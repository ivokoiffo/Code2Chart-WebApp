package com.grupo401.proyecto;

public class ASTContainer {
	int id;	
	int father;
	
	String content;
	String tipo;
	
	public ASTContainer(int id, int father, String content, String tipo) {
		this.id = id;
		this.father = father;
		this.content = content;
		this.tipo = tipo;
	}
	
	public int getId() {
		return id;
	}
	
	public int getFather() {
		return father;
	}
	
	public String getContent() {
		return content;
	}
	
	public String getTipo() {
		return tipo;
	}
	
}
