package com.grupo401.proyecto;

import org.antlr.v4.runtime.Token;

public class MyCVisitor {
	
	public void visit(AbstractSyntaxTreeConverter ast) {
		int i;
		switch(ast.getPayload().toString()){
			
			case "selectionStatement":
				/********************IF - ELSE********************/
				System.out.println("CONSULTA " + ast.getChildren().get(ast.findChildren("expression",0)).getChildrenContent());
				
				i = ast.findChildren("statement",0);
				visit(ast.getChildren().get(i));
				
				i++;
				if(ast.getChildren().size() > i) {
					//HAY UN ELSE
					if(ast.getChildren().get(i).getPayload() instanceof Token){
						
						Token token = (Token) ast.getChildren().get(i).getPayload();
						
						if(token.getText().equals("else")) {
							System.out.println("CONSULTA-ELSE ");
							visit(ast.getChildren().get(ast.findChildren("statement",1)));
						}
					}
				}
				
				
				
				System.out.println("CONSULTA-FIN ");
			break;
			
			case "iterationStatement":
				/********************FOR********************/
				System.out.println("CICLO "+ ast.getChildren().get(ast.findChildren("forCondition",0)).getChildrenContent());
				visit(ast.getChildren().get(ast.findChildren("statement",0)));
				System.out.println("CICLO-FIN ");
			break;
			
			//EXPRESIONES, FUNCIONES Y ASIGNACIONES VARIAS
			case "expression": case "initDeclaratorList":
				System.out.println("ASIGNACION/LLAMADA "+ ast.getChildrenContent());
			break;
		
			default:
				for (i = 0; i < ast.getChildren().size(); i++) {
		            if (!(ast.getPayload() instanceof Token)) {
		                //SOLO BAJAR AL HIJO SI NO ES UN TOKEN
		                visit(ast.getChildren().get(i));
		            }
				}
			break;
			
		}
	}
}
