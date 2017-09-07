package com.grupo401.proyecto;

import java.util.LinkedList;
import org.antlr.v4.runtime.Token;

public class MyCVisitor {
	
	public LinkedList<Integer> visit(AbstractSyntaxTreeConverter ast, LinkedList<Integer> father) {
		int i;
		Token token = null;
		
		if(father == null) {
			father = new LinkedList<Integer>();
			father.add(-1);
		}
		
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
						father = visit(ast.getChildren().get(i), ast.getIdAsList());
						
						i++;
						if(ast.getChildren().size() > i) {
							//HAY UN ELSE
							if(ast.getChildren().get(i).getPayload() instanceof Token){
								
								token = (Token) ast.getChildren().get(i).getPayload();
								
								if(token.getText().equals("else")) {
									
									System.out.println("CONSULTA-ELSE ");
									visit(ast.getChildren().get(ast.findChildren("statement",1)),ast.getIdAsList());
								}
							}
						}
						System.out.println("IF-FIN ");
						
					break;
					
					case "switch":
						/********************SWITCH********************/
						//TODO CONTROLAR, MEPA QUE ES CUALQUIERA
						
						ast.setPrevious(father);
						ast.setType("switch");
						
						System.out.println("SWITCH "+ ast.getChildren().get(ast.findChildren("expression",0)).getChildrenContent());
						int j = 0;
						LinkedList<Integer> newFather = father;
						
						while (ast.findChildren("case", j) != 0 ) {
							AbstractSyntaxTreeConverter newAst = ast.getChildren().get(ast.findChildren("case", j));
							
							newAst.setPrevious(newFather);
							newAst.setType("decisión");
							newAst.setContent(ast.getPayload().toString() + '=' + newAst.getPayload().toString());
							
							father = visit(ast.getChildren().get(ast.findChildren("statement",0)),ast.getIdAsList());
							
							j++;
							newFather = ast.getChildren().get(ast.findChildren("case", j)).getIdAsList();
						}
						
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
						father = visit(ast.getChildren().get(ast.findChildren("statement",0)),ast.getIdAsList());
						System.out.println("WHILE-FIN ");
					break;
					
					case "do":
						/********************DO********************/
						ast.setPrevious(father);
						ast.setType("do");
						ast.setContent(ast.getChildren().get(ast.findChildren("expression",0)).getChildrenContent());
						
						System.out.println("DO "+ ast.getChildren().get(ast.findChildren("expression",0)).getChildrenContent());
						father = visit(ast.getChildren().get(ast.findChildren("statement",0)),ast.getIdAsList());
						System.out.println("DO-FIN ");
					break;
					
					case "for":
						/********************FOR********************/
						ast.setPrevious(father);
						ast.setType("for");
						ast.setContent(ast.getChildren().get(ast.findChildren("forCondition",0)).getChildrenContent());
						
						System.out.println("FOR "+ ast.getChildren().get(ast.findChildren("forCondition",0)).getChildrenContent());
						father = visit(ast.getChildren().get(ast.findChildren("statement",0)),ast.getIdAsList());
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
				
				father = new LinkedList<Integer>();
				father.add(ast.getId());
			break;
		
			default:
				for (i = 0; i < ast.getChildren().size(); i++) {
		            if (!(ast.getPayload() instanceof Token)) {
		                
		            	//SOLO BAJAR AL HIJO SI NO ES UN TOKEN
		            	if(father.contains(ast.getId())){
		            		father = visit(ast.getChildren().get(i),ast.getIdAsList());
		            	} else {
		            		LinkedList<Integer> aux = new LinkedList<Integer>();
		            		aux = visit(ast.getChildren().get(i),father);
		            		
		            		aux.removeIf(a -> a == 0);
		            		father = aux;
		            	}
		            }
				}
			break;
			
		}
		return father;
	}
}
