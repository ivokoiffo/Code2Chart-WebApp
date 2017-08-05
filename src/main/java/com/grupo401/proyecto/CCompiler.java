package com.grupo401.proyecto;

import org.antlr.v4.runtime.*;

import cUtils.CLexer;
import cUtils.CParser;
import cUtils.CParser.CompilationUnitContext;
import exceptions.UnableToParseFileException;


public class CCompiler {
	
	public void compile(String file){
		try {
			ANTLRInputStream input = new ANTLRInputStream(file);

			CLexer lexer = new CLexer(input);

			CParser parser = new CParser(new CommonTokenStream(lexer));
			CParser.CompilationUnitContext tree = parser.compilationUnit();
			
			MyCListener myListener = new MyCListener();
			AbstractSyntaxTreeConverter ast = new AbstractSyntaxTreeConverter(tree);
			System.out.println(ast.toString()); // print LISP-style tree

		} catch (Exception e) {
			new UnableToParseFileException();
		}
	}
}


	