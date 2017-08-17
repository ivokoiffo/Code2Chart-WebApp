package com.grupo401.proyecto;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class XmlBuilderTest {
	
	private String fileName;
	private XmlBuilder builder;
	
	@Before
	public void setUp(){
		this.fileName = "pruebaBuilder";
		this.builder = new XmlBuilder(fileName);
	}
	
	@Test
	public void fileCreation(){
		assertTrue(this.builder.getFile().getName().equalsIgnoreCase(fileName));
	}
	
	@Test
	public void structureCreation(){
		builder.setXmlStructure().build();
	}
	
	@Test
	public void appendNode(){
		builder.setXmlStructure().appendNode(0, "decision", "inicio").build();
	}
	
	@Test
	public void appendLink(){
		builder.setXmlStructure().appendNode(0, "decision", "inicio").appendNode(1, "decision", "fin").appendLink(0, 1, "").build();
	}
}
