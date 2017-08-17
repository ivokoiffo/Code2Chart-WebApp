package com.grupo401.proyecto;

import org.antlr.v4.runtime.Token;

public class MyCVisitor {
	
	public int visit(AbstractSyntaxTreeConverter ast, int father) {
		int i;
		Token token = null;
		
		switch(ast.getPayload().toString()){
			
			case "selectionStatement":
				token = (Token) ast.getChildren().get(0).getPayload();
				switch(token.getText()){
					case "if":
						/********************IF - ELSE********************/
						System.out.println("IF " + ast.getChildren().get(ast.findChildren("expression",0)).getChildrenContent());
						
						ast.setPrevious(father);
						ast.setType("decisión");
						ast.setContent(ast.getChildren().get(ast.findChildren("expression",0)).getChildrenContent());
						
						i = ast.findChildren("statement",0);
						father = visit(ast.getChildren().get(i), ast.getId());
						
						i++;
						if(ast.getChildren().size() > i) {
							//HAY UN ELSE
							if(ast.getChildren().get(i).getPayload() instanceof Token){
								
								token = (Token) ast.getChildren().get(i).getPayload();
								
								if(token.getText().equals("else")) {
									
									System.out.println("CONSULTA-ELSE ");
									visit(ast.getChildren().get(ast.findChildren("statement",1)),ast.getId());
								}
							}
						}
						System.out.println("IF-FIN ");
						
					break;
					
					case "switch":
						/********************SWITCH********************/
						ast.setPrevious(father);
						ast.setType("decisión");
						
						System.out.println("SWITCH "+ ast.getChildren().get(ast.findChildren("expression",0)).getChildrenContent());
						
						//TODO MANEJAR LOS CASE
						
						System.out.println("SWITCH-FIN ");
					break;
				}
			break;
			
			case "iterationStatement":
				token = (Token) ast.getChildren().get(0).getPayload();
				switch(token.getText()){
					case "while":
						/********************WHILE********************/
						ast.setPrevious(father);
						ast.setType("while");
						ast.setContent(ast.getChildren().get(ast.findChildren("expression",0)).getChildrenContent());
						
						System.out.println("WHILE "+ ast.getChildren().get(ast.findChildren("expression",0)).getChildrenContent());
						father = visit(ast.getChildren().get(ast.findChildren("statement",0)),ast.getId());
						System.out.println("WHILE-FIN ");
					break;
					
					case "do":
						/********************DO********************/
						ast.setPrevious(father);
						ast.setType("do");
						ast.setContent(ast.getChildren().get(ast.findChildren("expression",0)).getChildrenContent());
						
						System.out.println("DO "+ ast.getChildren().get(ast.findChildren("expression",0)).getChildrenContent());
						father = visit(ast.getChildren().get(ast.findChildren("statement",0)),ast.getId());
						System.out.println("DO-FIN ");
					break;
					
					case "for":
						/********************FOR********************/
						ast.setPrevious(father);
						ast.setType("for");
						ast.setContent(ast.getChildren().get(ast.findChildren("forCondition",0)).getChildrenContent());
						
						System.out.println("FOR "+ ast.getChildren().get(ast.findChildren("forCondition",0)).getChildrenContent());
						father = visit(ast.getChildren().get(ast.findChildren("statement",0)),ast.getId());
						System.out.println("FOR-FIN ");
					break;
				}
			break;
			
			//EXPRESIONES, FUNCIONES Y ASIGNACIONES VARIAS
			case "expression": case "initDeclaratorList":
				System.out.println("ASIGNACION/LLAMADA "+ ast.getChildrenContent());
				
				ast.setPrevious(father);
				ast.setType("declaración de variables");
				ast.setContent(ast.getChildrenContent());
				
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
