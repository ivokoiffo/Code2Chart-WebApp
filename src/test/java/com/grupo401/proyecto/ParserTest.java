package com.grupo401.proyecto;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;

import org.junit.Test;

import cUtils.CCompiler;

public class ParserTest {
	
	private String printFile = "/home/nico/Escritorio/testFiles/hello.c";
	
	@Test
	public void testCompiler() throws Exception {
		String filePreParse = Files.lines(Paths.get(printFile)).collect(Collectors.joining());
				
		CCompiler compiler = new CCompiler();
		compiler.compile(filePreParse);
	}
}
