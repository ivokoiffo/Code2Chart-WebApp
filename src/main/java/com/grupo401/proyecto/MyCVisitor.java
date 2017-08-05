package com.grupo401.proyecto;

import org.antlr.v4.runtime.Token;

public class MyCVisitor {
	
	public void visit(AbstractSyntaxTreeConverter ast) {
		
		switch(ast.getPayload().toString()){
			case "selectionStatement":
				System.out.println("CONSULTA");
			break;
			case "iterationStatement":
				System.out.println("CICLO");
			break;
			case "expression":
				System.out.println("ASIGNACION/LLAMADA: " + ast.getPayload());
			break;
			default:
				for (int i = 0; i < ast.getChildren().size(); i++) {
	                if (!(ast.getPayload() instanceof Token)) {
	                    // Only traverse down if the payload is not a Token.
	                    visit(ast.getChildren().get(i));
	                }
	            }
			break;
		}
	}
	
}
