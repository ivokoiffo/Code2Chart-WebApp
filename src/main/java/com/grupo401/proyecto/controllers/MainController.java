package com.grupo401.proyecto.controllers;

import java.io.InputStream;
import java.util.LinkedList;

import javax.validation.Valid;
import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

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
	
	private FileHelper fileHelper = new FileHelper();

	@RequestMapping(value={"/", "*"})
	public String viewHome() {
		return "index";
	}
	
	@RequestMapping(value="/api/generarDiagrama", method=RequestMethod.POST,
			produces = MediaType.IMAGE_PNG_VALUE,consumes = "multipart/form-data")
	public @ResponseBody ResponseEntity<byte[]> generateDiagram(@RequestPart("model") @Valid FormData form,
												@RequestPart("file")  @Valid MultipartFile file) {
				
		String fileContent;
		try {
			if(!(form.getGithubUrl().isEmpty())){
				fileContent = this.fileHelper.getContentFromGithub(form.getGithubUrl());
			}else{
				fileContent = this.fileHelper.getContentFromMultipart(file);
			}
						
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
			
			return new ResponseEntity<byte[]>(IOUtils.toByteArray(image), HttpStatus.OK);

		}catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}
