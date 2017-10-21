package com.grupo401.proyecto;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.stream.Collectors;

import org.junit.Test;

import com.grupo401.proyecto.diagram.MyDiagram;

import exceptions.UnableToCreateFileException;


public class ParserTest {
	
	@Test
	public void testCompiler1() throws Exception {
		File file = new File("hello1.c");
		String path = file.getAbsolutePath();
		
		String filePreParse = Files.lines(Paths.get(path)).collect(Collectors.joining());
		
		CCompiler compiler = new CCompiler();
		AbstractSyntaxTreeConverter ast = compiler.compile(filePreParse);
		
		MyCVisitor visitor = new MyCVisitor();
		visitor.visit(ast,null);
		
		ParserToXmlAdapter adapter = new ParserToXmlAdapter();
		LinkedList<ASTContainer> list = adapter.getConvertedList(ast);
		
		XmlBuilder builder = new XmlBuilder("xml1");
		builder.setXmlStructure();
		
		list.forEach(a-> builder.appendNode(a.getId(), a.getTipo(), a.getContent()).appendLink(a.getFather(), a.getId(), ""));
		builder.build();
		
		MyDiagram mainFrame = new MyDiagram(builder.getFile().getAbsolutePath(), "diagrama1.png",null,null);
//		mainFrame.setVisible(true);
	}
	
	@Test
	public void testCompiler2() throws Exception {
		File file = new File("hello2.c");
		String path = file.getAbsolutePath();
		
		String filePreParse = Files.lines(Paths.get(path)).collect(Collectors.joining());
		
		CCompiler compiler = new CCompiler();
		AbstractSyntaxTreeConverter ast = compiler.compile(filePreParse);
		
		MyCVisitor visitor = new MyCVisitor();
		visitor.visit(ast,null);
		
		ParserToXmlAdapter adapter = new ParserToXmlAdapter();
		LinkedList<ASTContainer> list = adapter.getConvertedList(ast);
		
		XmlBuilder builder = new XmlBuilder("xml2");
		builder.setXmlStructure();
		
		list.forEach(a-> {
			builder.appendNode(a.getId(), a.getTipo(), a.getContent());
			if (a.getTipo() == "decisión") {
				builder.appendLink(a.getFather(), a.getId(), "decisión");
			} else {
				builder.appendLink(a.getFather(), a.getId(), "");
			}
		});
		builder.build();
		
		MyDiagram mainFrame = new MyDiagram(builder.getFile().getAbsolutePath(), "diagrama2.png",null,null);
//		mainFrame.setVisible(true);
	}
	
	@Test
	public void testCompiler3() throws Exception {
		File file = new File("hello3.c");
		String path = file.getAbsolutePath();
		
		String filePreParse = Files.lines(Paths.get(path)).collect(Collectors.joining());
		
		CCompiler compiler = new CCompiler();
		AbstractSyntaxTreeConverter ast = compiler.compile(filePreParse);

		System.out.println(ast.toString());
		
		MyCVisitor visitor = new MyCVisitor();
		visitor.visit(ast,null);
		
		ParserToXmlAdapter adapter = new ParserToXmlAdapter();
		LinkedList<ASTContainer> list = adapter.getConvertedList(ast);
		
		XmlBuilder builder = new XmlBuilder("xml3");
		builder.setXmlStructure();
		
		list.forEach(a-> {
			builder.appendNode(a.getId(), a.getTipo(), a.getContent());
			if (a.getTipo() == "decisión") {
				builder.appendLink(a.getFather(), a.getId(), "decisión");
			} else {
				builder.appendLink(a.getFather(), a.getId(), "");
			}
		});
		builder.build();
		
		MyDiagram mainFrame = new MyDiagram(builder.getFile().getAbsolutePath(), "diagrama3.png",null,null);
//		mainFrame.setVisible(true);
	}
	
	@Test
	public void testCompiler4() throws Exception {
		File file = new File("hello4.c");
		String path = file.getAbsolutePath();
		
		String filePreParse = Files.lines(Paths.get(path)).collect(Collectors.joining());
		
		CCompiler compiler = new CCompiler();
		AbstractSyntaxTreeConverter ast = compiler.compile(filePreParse);

		System.out.println(ast.toString());
		
		MyCVisitor visitor = new MyCVisitor();
		visitor.visit(ast,null);
		
		ParserToXmlAdapter adapter = new ParserToXmlAdapter();
		LinkedList<ASTContainer> list = adapter.getConvertedList(ast);
		
		XmlBuilder builder = new XmlBuilder("xml4");
		builder.setXmlStructure();
		
		list.forEach(a-> {
			builder.appendNode(a.getId(), a.getTipo(), a.getContent());
			if (a.getTipo() == "decisión") {
				builder.appendLink(a.getFather(), a.getId(), "decisión");
			} else {
				builder.appendLink(a.getFather(), a.getId(), "");
			}
		});
		builder.build();
		
		MyDiagram mainFrame = new MyDiagram(builder.getFile().getAbsolutePath(), "diagrama4.png",null,null);
//		mainFrame.setVisible(true);
	}
}
