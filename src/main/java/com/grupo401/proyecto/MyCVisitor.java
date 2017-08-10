package com.grupo401.proyecto;

import org.antlr.v4.runtime.Token;

public class MyCVisitor {
	
	public int visit(AbstractSyntaxTreeConverter ast, int father) {
		int i;
		
		switch(ast.getPayload().toString()){
			
			case "selectionStatement":
				/********************IF - ELSE********************/
				System.out.println("CONSULTA " + ast.getChildren().get(ast.findChildren("expression",0)).getChildrenContent());
				
				ast.setPrevious(father);
				
				i = ast.findChildren("statement",0);
				father = visit(ast.getChildren().get(i), ast.getId());
				
				i++;
				if(ast.getChildren().size() > i) {
					//HAY UN ELSE
					if(ast.getChildren().get(i).getPayload() instanceof Token){
						
						Token token = (Token) ast.getChildren().get(i).getPayload();
						
						if(token.getText().equals("else")) {
							
							System.out.println("CONSULTA-ELSE ");
							visit(ast.getChildren().get(ast.findChildren("statement",1)),ast.getId());
						}
					}
				}
				System.out.println("CONSULTA-FIN ");
				
			break;
			
			case "iterationStatement":
				Token token = (Token) ast.getChildren().get(0).getPayload();
				switch(token.getText()){
					case "while":
						
					break;
					
					case "do":
						
					break;
					
					case "for":
						/********************FOR********************/
						ast.setPrevious(father);
						
						System.out.println("CICLO "+ ast.getChildren().get(ast.findChildren("forCondition",0)).getChildrenContent());
						father = visit(ast.getChildren().get(ast.findChildren("statement",0)),ast.getId());
						System.out.println("CICLO-FIN ");
						
					break;
				}
			break;
			
			//EXPRESIONES, FUNCIONES Y ASIGNACIONES VARIAS
			case "expression": case "initDeclaratorList":
				System.out.println("ASIGNACION/LLAMADA "+ ast.getChildrenContent());
				ast.setPrevious(father);
				father = ast.getId();
			break;
		
			default:
				for (i = 0; i < ast.getChildren().size(); i++) {
		            if (!(ast.getPayload() instanceof Token)) {
		                
		            	//SOLO BAJAR AL HIJO SI NO ES UN TOKEN
		            	if(father == ast.getId()){
		            		father = visit(ast.getChildren().get(i),ast.getId());
		            	} else {
		            		int aux = 0;
		            		aux = visit(ast.getChildren().get(i),father);
		            		
		            		if(aux != 0){
		            			father = aux;
		            		}
		            	}
		            }
				}
			break;
			
		}
		return father;
	}
}
