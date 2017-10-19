package com.grupo401.proyecto.controllers;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.grupo401.proyecto.ASTContainer;
import com.grupo401.proyecto.AbstractSyntaxTreeConverter;
import com.grupo401.proyecto.CCompiler;
import com.grupo401.proyecto.FormData;
import com.grupo401.proyecto.MyCVisitor;
import com.grupo401.proyecto.ParserToXmlAdapter;
import com.grupo401.proyecto.XmlBuilder;
import com.grupo401.proyecto.diagram.MyDiagram;
import com.grupo401.proyecto.githubHelper.FileHelper;

@Controller
public class MainController {
	
	
	@RequestMapping(value={"/", "*"})
	public String viewHome() {
		return "index";
	}
	
	@RequestMapping(value="/api/generarDiagrama", method=RequestMethod.POST, consumes="application/json")
	public ResponseEntity<MyDiagram> generateDiagram(@RequestBody FormData form){
		
		
		FileHelper.getInstance();
		String fileContent;
		try {
			if(!form.getGithubUrl().isEmpty()){
				fileContent = FileHelper.escribirEnFS(form.getGithubUrl());
			}else{
				JSONPObject file = (JSONPObject) form.getLocalPath();
				//String path = file.getAbsolutePath();
				
				fileContent  = null;//Files.lines(Paths.get(path)).collect(Collectors.joining());
			}
			
			//File file = new File(form.getName());
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
			
			MyDiagram mainFrame = new MyDiagram(builder.getFile().getAbsolutePath(), 
					form.getName().concat(".png"),form.getAuthor(),form.getDescription());
			mainFrame.setVisible(true);
			
			return new ResponseEntity<>(mainFrame,HttpStatus.OK);
		} catch (IOException e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
}
