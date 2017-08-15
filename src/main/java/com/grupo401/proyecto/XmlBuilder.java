package com.grupo401.proyecto;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class XmlBuilder {
	
	private Document doc;
	private File file;
	private TransformerFactory transformerFactory;
	private Transformer transformer;
	private DOMSource source;
	private StreamResult result;

	public XmlBuilder(String fileName){
		this.file = new File("/tmp", fileName);
	}
	
	public XmlBuilder setXmlStructure(int nodesNumber,int linksNumber){
		try {
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
		    DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
		    
		    // root elements
		    doc = docBuilder.newDocument();
		    Element rootElement = doc.createElement("Graph");
		    Element nodeElement = doc.createElement("Nodes");
		    Element linksElement = doc.createElement("Links");
		    
		    doc.appendChild(rootElement);
		    rootElement.appendChild(nodeElement);
		    // append the number of nodes depending on each diagram
		    int i;
		    for(i=0;i<nodesNumber;i++){
		    	Element childNode = doc.createElement("Node");
		    	childNode.setAttribute("id", String.valueOf(i));
		    	childNode.setAttribute("nombre", "");
		    	childNode.setAttribute("tipo", "");
		    	nodeElement.appendChild(childNode);
		    }
		    
		    // append the number of links depending on each diagram
		    rootElement.appendChild(linksElement);
		    for(i=0;i<linksNumber;i++){
		    	Element childLink = doc.createElement("Link");
		    	childLink.setAttribute("id", String.valueOf(i));
		    	childLink.setAttribute("origin", "");
		    	childLink.setAttribute("target", "");
		    	childLink.setAttribute("tagLink", "");
		    	linksElement.appendChild(childLink);
		    }
		    
		    
		    transformerFactory = TransformerFactory.newInstance();
		    transformer = transformerFactory.newTransformer();
		    source = new DOMSource(doc);
		    result = new StreamResult(new File("/home/nico/Escritorio/test.xml"));

		    // Output to console for testing
		    //StreamResult result = new StreamResult(System.out);

		    transformer.transform(source, result);
		    
		    return this;
		} catch (Exception e) {
			e.printStackTrace();
			return this;
		}
	}
	
	/*<Graph>
    <Nodes>
        <Node id="0" nombre="inicio" tipo="inicio" />*/

	/*public void setNodeProperties(String nodeName,String shapeName,String nodeType) {
		
	}*/

	public XmlBuilder setNodeShape(int idValue, String shape) {
		Element node = (Element)doc.getElementsByTagName("Node").item(idValue);
		node.setAttribute("tipo", shape);
		return this;
	}
	
	public XmlBuilder setNodeName(int idValue, String name) {
		Element node = (Element)doc.getElementsByTagName("Node").item(idValue);
		node.setAttribute("nombre", name);
		return this;
	}

	public XmlBuilder setLinkSourceId(int idValue, int sourceId) {
		Element node = (Element)doc.getElementsByTagName("Link").item(idValue);
		node.setAttribute("origin", String.valueOf(sourceId));
		return this;
	}

	public XmlBuilder setLinkDestId(int idValue, int destId) {
		Element node = (Element)doc.getElementsByTagName("Link").item(idValue);
		node.setAttribute("target", String.valueOf(destId));
		return this;
	}

	public XmlBuilder build() {
		
		return this;
	}
	
	/*--------------------GETTERS PARA TESTS-----------------*/
	public File getFile() {
		return file;
	}

}
