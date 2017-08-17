package com.grupo401.proyecto;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;

import org.junit.Test;


public class ParserTest {
	
	File file = new File("hello3.c");
	
	private String path = file.getAbsolutePath();
	
	@Test
	public void testCompiler() throws Exception {
		String filePreParse = Files.lines(Paths.get(path)).collect(Collectors.joining());
		
		CCompiler compiler = new CCompiler();
		AbstractSyntaxTreeConverter ast = compiler.compile(filePreParse);
		
		MyCVisitor visitor = new MyCVisitor();
		
		visitor.visit(ast,ast.getId());
		
		System.out.println(ast.toString());
	}
}
