package com.grupo401.proyecto;

import java.util.LinkedList;

public class ParserToXmlAdapter {
	LinkedList<ASTContainer> list;
	
	public ParserToXmlAdapter() {
		list = new LinkedList<>();
	}
	public LinkedList<ASTContainer> getConvertedList(AbstractSyntaxTreeConverter ast){
		
		ASTContainer cont = new ASTContainer(-1, 0, "Inicio", "inicio");
		list.add(cont);
		convert(ast);
		
		cont = new ASTContainer(list.getLast().getId()+1, list.getLast().getId(), "Fin", "fin");
		list.add(cont);
		
		return list;
	}
	
	private void convert(AbstractSyntaxTreeConverter ast) {
		if(ast.getPrevious()!=0) {
			ASTContainer container = new ASTContainer(ast.getId(), ast.getPrevious(), ast.getType(), ast.getContent());
			list.add(container);
		}
		ast.getChildren().forEach(c-> convert(c));
	}
}
