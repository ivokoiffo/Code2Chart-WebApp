package com.grupo401.proyecto;

import org.antlr.v4.runtime.*;

import cUtils.CLexer;
import cUtils.CParser;
import exceptions.UnableToParseFileException;


public class CCompiler {
	
	public AbstractSyntaxTreeConverter compile(String file){
		AbstractSyntaxTreeConverter ast = null;
		
		try {
			CharStream input = CharStreams.fromString(file);
			CLexer lexer = new CLexer(input);
			CParser parser = new CParser(new CommonTokenStream(lexer));
			
			CParser.CompilationUnitContext tree = parser.compilationUnit();
			
			ast = new AbstractSyntaxTreeConverter(tree);
			
			ast.setID(0);

		} catch (Exception e) {
			new UnableToParseFileException();
		}
		
		return ast;
	}
}


	