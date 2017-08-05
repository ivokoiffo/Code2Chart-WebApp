package com.grupo401.proyecto;

import org.antlr.v4.runtime.Token;

public class MyCVisitor {
	
	public void visit(AbstractSyntaxTreeConverter ast) {
		
		switch(ast.getPayload().toString()){
		//TRES CASOS DISTINTOS: IF, SWITCH Y ?:
			case "selectionStatement":
				System.out.println("CONSULTA");
			break;
			
			//TRES CASOS DISTINTOS: FOR, WHILE Y DO WHILE			
			case "forCondition":
				System.out.println("CICLO: " + ast.getChildrenContent());
			break;
			
			//EXPRESIONES, FUNCIONES Y ASIGNACIONES VARIAS			
			case "expression": case "initDeclaratorList":
				System.out.println("ASIGNACION/LLAMADA: " + ast.getChildrenContent());
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
