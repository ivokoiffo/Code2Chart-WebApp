package com.grupo401.proyecto.controllers;

import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import org.apache.commons.io.IOUtils;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.grupo401.proyecto.ASTContainer;
import com.grupo401.proyecto.AbstractSyntaxTreeConverter;
import com.grupo401.proyecto.CCompiler;
import com.grupo401.proyecto.FormData;
import com.grupo401.proyecto.MyCVisitor;
import com.grupo401.proyecto.ParserToXmlAdapter;
import com.grupo401.proyecto.XmlBuilder;
import com.grupo401.proyecto.Helpers.FileHelper;
import com.grupo401.proyecto.Helpers.ImageHelper;
import com.grupo401.proyecto.diagram.MyDiagram;

@Controller
public class MainController {
	
	
	@RequestMapping(value={"/", "*"})
	public String viewHome() {
		return "index";
	}
	
	@RequestMapping(value="/api/generarDiagrama", method=RequestMethod.POST, consumes="application/json",
			produces = MediaType.IMAGE_PNG_VALUE)
	public @ResponseBody byte[] generateDiagram(@RequestBody FormData form) throws IOException{
		
		
		FileHelper.getInstance();
		String fileContent;
		try {
			if(!form.getGithubUrl().isEmpty()){
				fileContent = FileHelper.escribirEnFS(form.getGithubUrl());
			}else{
				fileContent  = null;//Files.lines(Paths.get(path)).collect(Collectors.joining());
			}
			
			//File file = new File(form.getLocalPath().toString());
			//String path = file.getAbsolutePath();
			
			//String filePreParse = Files.lines(Paths.get(path)).collect(Collectors.joining());
			
			
			CCompiler compiler = new CCompiler();
			AbstractSyntaxTreeConverter ast = compiler.compile(fileContent);
			
			MyCVisitor visitor = new MyCVisitor();
			visitor.visit(ast,null);
			
			ParserToXmlAdapter adapter = new ParserToXmlAdapter();
			LinkedList<ASTContainer> list = adapter.getConvertedList(ast);
			
			XmlBuilder builder = new XmlBuilder("xml1");
			builder.setXmlStructure();
			
			list.forEach(a-> builder.appendNode(a.getId(), a.getTipo(), a.getContent()).appendLink(a.getFather(), a.getId(), ""));
			builder.build();
			
			new MyDiagram(builder.getFile().getAbsolutePath(),form.getName().concat(".png"),form.getAuthor(), null);
			
			InputStream image = ImageHelper.getInstance().doGet(form.getName().concat(".png"));
			
			return IOUtils.toByteArray(image);
						
			
		}catch (IOException e) {
			return null;
		}
	}
}
