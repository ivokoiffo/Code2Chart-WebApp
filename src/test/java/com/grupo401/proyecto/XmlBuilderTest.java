package com.grupo401.proyecto;

import static org.junit.Assert.*;

import java.util.LinkedList;

import org.junit.Before;
import org.junit.Test;

public class XmlBuilderTest {
	
	private String fileName;
	private XmlBuilder builder;
	
	@Before
	public void setUp(){
		this.fileName = "xml3";
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
		LinkedList<Integer> end = new LinkedList<Integer>();
		end.add(0);
		
		builder.setXmlStructure().appendNode(0, "decision", "inicio").appendNode(1, "decision", "fin").appendLink(end, 1, "").build();
	}
}
