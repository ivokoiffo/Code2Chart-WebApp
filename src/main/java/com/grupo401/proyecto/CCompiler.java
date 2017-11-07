package com.grupo401.proyecto;

import org.antlr.v4.runtime.*;

import cUtils.CLexer;
import cUtils.CParser;


public class CCompiler {
	
	public AbstractSyntaxTreeConverter compile(String file){
		AbstractSyntaxTreeConverter ast = null;
		
		try {
			CharStream input = CharStreams.fromString(file);
			CLexer lexer = new CLexer(input);
			CParser parser = new CParser(new CommonTokenStream(lexer));
			
			CParser.CompilationUnitContext tree = parser.compilationUnit();
			
			ast = new AbstractSyntaxTreeConverter(tree);
			visit(ast);
			ast.setID(0);
			
		} catch (Exception e) {
		}
		
		return ast;
	}

	private void visit(AbstractSyntaxTreeConverter codeAst) {
		int i;
		
		if(codeAst.getPayload().toString().equalsIgnoreCase("externalDeclaration")){
			if(codeAst.getChildren().get(0).getPayload().toString().equalsIgnoreCase("functionDefinition")){
				
				String name = getFunctionName(codeAst);
				
				AbstractSyntaxTreeConverter beginAst = new AbstractSyntaxTreeConverter("inicio");
				beginAst.setContent("Inicio " + name);
				
				AbstractSyntaxTreeConverter endAst = new AbstractSyntaxTreeConverter("fin");
				endAst.setContent("Fin " + name);
				
				codeAst.addChildren(beginAst,0);
				codeAst.addChildren(endAst);
				
				for (i = 0; i < codeAst.getChildren().size(); i++) {
		            if (!(codeAst.getPayload() instanceof Token)) {
		            	visit(codeAst.getChildren().get(i));
		            }
				}
			}
		} else {			
			for (i = 0; i < codeAst.getChildren().size(); i++) {
	            if (!(codeAst.getPayload() instanceof Token)) {
	            	visit(codeAst.getChildren().get(i));
	            }
			}
		}
	}

	private String getFunctionName(AbstractSyntaxTreeConverter codeAst) {
		int i;
		String name = null;
		
		for (i = 0; i < codeAst.getChildren().size(); i++) {
			if(name!=null) {
				return name;
			}
			
            if (!(codeAst.getPayload().toString().equalsIgnoreCase("declarator"))) {
            	name = getFunctionName(codeAst.getChildren().get(i));
            } else {
            	return codeAst.getChildren().get(codeAst.findChildren("declarator",0)).getChildrenContent();
            }
		}
		
		return name;
	}
}


	