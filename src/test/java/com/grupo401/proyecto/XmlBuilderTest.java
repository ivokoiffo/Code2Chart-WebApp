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
		builder.setXmlStructure(2, 1).build();
	}
	
	@Test
	public void setNodeName(){
		builder.setXmlStructure(2, 1).setNodeContent(0, "inicio").setNodeShape(0, "decision").build();
		
	}
	
	@Test
	public void setNodeShape(){
		builder.setXmlStructure(2, 1).setNodeShape(0, "decision").build();
	}
}
