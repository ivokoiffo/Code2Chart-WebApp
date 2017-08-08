package com.grupo401.proyecto;

import org.antlr.v4.runtime.Token;

public class MyCVisitor {
	
	public void visit(AbstractSyntaxTreeConverter ast) {
		
		switch(ast.getPayload().toString()){
		//TRES CASOS DISTINTOS: IF, SWITCH Y ?:
			case "selectionStatement":
				//IF
				System.out.println("CONSULTA " + ast.findChildren("expression").getChildrenContent());
				visit(ast.findChildren("statement"));
				
				//ELSE
				for(int i=0;i<ast.getChildren().size();i++) {
					if(ast.getChildren().get(i).getPayload() instanceof Token){
						
						Token token = (Token) ast.getChildren().get(i).getPayload();
						
						if(token.getText().equals("else")) {
							System.out.println("CONSULTA-ELSE ");
							i++;
							visit(ast.getChildren().get(i));
							System.out.println("CONSULTA-FIN ");
						}
					}
				}
			break;
			
			//TRES CASOS DISTINTOS: FOR, WHILE Y DO WHILE			
			case "iterationStatement":
				//FOR
				System.out.println("CICLO "+ ast.findChildren("forCondition").getChildrenContent());
				visit(ast.findChildren("statement"));
				System.out.println("CICLO-FIN ");
			break;
			
			//EXPRESIONES, FUNCIONES Y ASIGNACIONES VARIAS
			case "expression": case "initDeclaratorList":
				System.out.println("ASIGNACION/LLAMADA "+ ast.getChildrenContent());
			break;
		
			default:
				for (int i = 0; i < ast.getChildren().size(); i++) {
		            if (!(ast.getPayload() instanceof Token)) {
		                //SOLO BAJAR AL HIJO SI NO ES UN TOKEN
		                visit(ast.getChildren().get(i));
		            }
				}
			break;
			
		}
	}
}
