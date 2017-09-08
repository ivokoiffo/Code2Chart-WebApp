package com.grupo401.proyecto;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.stream.Collectors;

import org.junit.Test;

import com.grupo401.proyecto.diagram.MyDiagram;


public class ParserTest {
	
	@Test
	public void testCompiler() throws Exception {
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
		
		list.forEach(a-> builder.appendNode(a.getId(), a.getContent(), a.getTipo()).appendLink(a.getFather(), a.getId(), ""));
		builder.build();
		
		MyDiagram mainFrame = new MyDiagram(builder.getFile().getAbsolutePath());
		mainFrame.setVisible(true);
		

		File file22 = new File("hello1.c");
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
		
		list.forEach(a-> builder.appendNode(a.getId(), a.getContent(), a.getTipo()).appendLink(a.getFather(), a.getId(), ""));
		builder.build();
	}
}
