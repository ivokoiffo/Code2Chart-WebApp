package com.grupo401.proyecto.diagram;
 
import java.awt.geom.* ; 
import java.io.*;
import java.util.*;

import javax.xml.parsers.*;

import org.w3c.dom.*;

import com.mindfusion.diagramming.*;

public class MyDiagram {

	private NodeHandler manejador = new NodeHandler();
	private HashMap<String, DiagramNode> nodeMap = new HashMap<String, DiagramNode>();
	private static final long serialVersionUID = 1L;
	
	private Diagram diagram = new Diagram();
	//private DiagramView view = new DiagramView();
	
	private ArrayList<ContainerNode> listaDeBucles = new ArrayList<>();
    private ArrayList<ArrayList<DiagramNode>> listasDeNodosParaBucles = new ArrayList<>();

    List<String> nodosDecision = new ArrayList<String>();
    List<String> nodosNoDecision = new ArrayList<String>();
    
    private int cantidadTotalDeBucles = 0;
    
	public MyDiagram(String xmlPath, String nombreDelDiagrama, String author, String description){
		
		loadGraph(diagram, xmlPath);
		
		HtmlBuilder creador = new HtmlBuilder(diagram);
		try {
			
			creador.createImageHtml("index.html", "Code2Chart", nombreDelDiagrama,"./", "png");

		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	
	void loadGraph(Diagram diagram, String filepath){
		
		Rectangle2D.Float bounds = new Rectangle2D.Float(0, 0, 20, 10);
		
		// load the graph XML
		Document document = loadXmlFile(filepath);
		Element root = document.getDocumentElement();

		// traigo todos los nodos, y todos los links
		NodeList nodes = root.getElementsByTagName("Node");
		NodeList links = root.getElementsByTagName("Link");
		
		dibujarLosNodosYClasificarlos(nodes, bounds);
		
		List<String> nodosYaLinkeados = new ArrayList<String>(); //para mapear de 1 sola vez
		// mapeo los links
		for (int i = 0; i < links.getLength(); ++i){
			
			Element link = (Element)links.item(i);
			DiagramNode origin = nodeMap.get(link.getAttribute("origin"));
			if (!esNodoDecision(link.getAttribute("origin"), nodosDecision)){
				//es un nodo comun
				DiagramNode target = nodeMap.get(link.getAttribute("target"));
				diagram.getFactory().createDiagramLink(origin, target);
				nodosYaLinkeados.add(link.getAttribute("origin"));
			}else{
				//primero me fijo si ya fueron mapeados sus links
				//entrando a esta parte significa que es un nodo de decision
				if (!nodosYaLinkeados.contains(link.getAttribute("origin"))) {
				List<String> idsTarget = new ArrayList<String>();
				idsTarget = obtenerNodosTargetsDadoUnNodoOrigenDeDecision(link.getAttribute("origin"), links);
				DiagramNode target1 = nodeMap.get(idsTarget.get(0));
				DiagramNode target2 = nodeMap.get(idsTarget.get(1));
				diagram.getFactory().createDiagramLink(origin, target1).setText("SI");
				diagram.getFactory().createDiagramLink(origin, target2).setText("NO");
				nodosYaLinkeados.add(link.getAttribute("origin"));
				}
			}
		}
		
		armarElLayout();
		
		for(int i = listaDeBucles.size()-1; i >= 0; --i){
            for (int k = 0; k < listasDeNodosParaBucles.get(i).size(); ++k) {
                listaDeBucles.get(i).add(listasDeNodosParaBucles.get(i).get(k));
            }
            armarElLayout();
        }
		
	}
	
	public void armarElLayout(){
        //Conn esto, menciono que si bien tome un layout de Decision, tambien tengo que mapear todas las relaciones de cada
        //uno de los nodos, es decir si hay uno que es decision, necesariamente tengo que crear los 2 links de decision seguidos,
        //no uno, y luego otro.
		LayeredLayout layout = new LayeredLayout();
		layout.setLayerDistance(10);
		layout.setNodeDistance(25);
		layout.setStraightenLongLinks(true);

		layout.arrange(diagram);
        
        diagram.setShadowsStyle(ShadowsStyle.None);
        
        Rectangle2D medidaDiagrama = diagram.getContentBounds(false, true);
        diagram.setBounds(medidaDiagrama);

    }
	
	public void dibujarLosNodosYClasificarlos(NodeList nodes, Rectangle2D.Float bounds){

        String idBucle = null;
        int enDonde = 0;

        for (int i = 0; i < nodes.getLength(); ++i) {

            Element node = (Element) nodes.item(i);
            //nuevocodigo
            String tipo = node.getAttribute("tipo");
            switch (tipo) {
                case "decisión":
                    nodosDecision.add(node.getAttribute("id"));
                    break;

                case "bucle":

                    nodosNoDecision.add(node.getAttribute("id"));
                    idBucle = node.getAttribute("id");

                    ContainerNode nodoBuclePrimitivo = dibujarUnNodoBucle(bounds, node, nodeMap);
                    listaDeBucles.add(nodoBuclePrimitivo);

                    listasDeNodosParaBucles.add(new ArrayList<>());

                    for(int j = i + 1; !((Element) nodes.item(j)).getAttribute("tipo").equals("finBucle"+idBucle); ++j){

                        Element nodoInside = (Element) nodes.item(j);

                        String tipoEnBucle = nodoInside.getAttribute("tipo");

                        switch (tipoEnBucle){
                            case "bucle":
                                ContainerNode nodoContainer  = dibujarUnNodoBucle(bounds, nodoInside, nodeMap);
                                listasDeNodosParaBucles.get(enDonde).add(nodoContainer);
                                listasDeNodosParaBucles.add(new ArrayList<>());
                                ++enDonde;
                                ++cantidadTotalDeBucles;
                                listaDeBucles.add(nodoContainer);
                                nodosNoDecision.add(nodoInside.getAttribute("id"));
                                break;

                            case "proceso":
                                ShapeNode nodoProceso = dibujarUnNodo(bounds, nodoInside, nodeMap);
                                listasDeNodosParaBucles.get(enDonde).add(nodoProceso);
                                nodosNoDecision.add(nodoInside.getAttribute("id"));
                                break;

                            case "decisión":
                                ShapeNode nodoDecision = dibujarUnNodo(bounds, nodoInside, nodeMap);
                                listasDeNodosParaBucles.get(enDonde).add(nodoDecision);
                                nodosDecision.add(nodoInside.getAttribute("id"));
                                break;

                            default:
                                --enDonde;
                                break;
                        }

                        i=j;
                    }

                    ++cantidadTotalDeBucles;
                    enDonde = cantidadTotalDeBucles;
                    break;

                default:
                    if (!node.getAttribute("tipo").matches(".*\\d+.*")) {
                        nodosNoDecision.add(node.getAttribute("id"));
                    }
                    break;
            }

            if (!node.getAttribute("tipo").matches(".*\\d+.*") && !tipo.equals("bucle")) {
                dibujarUnNodo(bounds, node, nodeMap);
            }
        }
	}

	public ShapeNode dibujarUnNodo(Rectangle2D.Float bounds, Element node, HashMap<String, DiagramNode> nodeMapFuncion){

        ShapeNode diagramNode = diagram.getFactory().createShapeNode(bounds);

        //Convierte el "tipo" ubicado en el xml en la forma
        manejador.conversor(node, diagramNode);
        String idNodo = node.getAttribute("id");
        nodeMapFuncion.put(idNodo, diagramNode);
        diagramNode.setText(node.getAttribute("nombre"));

        //Clave para que se vea bien el texto dentro del nodo
        diagramNode.resizeToFitText(FitSize.KeepRatio);

        return diagramNode;
    }
	
	public ContainerNode dibujarUnNodoBucle(Rectangle2D.Float bounds, Element node,  HashMap<String, DiagramNode> nodeMapFuncion){

        ContainerNode bucle = diagram.getFactory().createContainerNode(bounds);
        bucle.setAutoShrink(true);

        //Convierte el "tipo" ubicado en el xml en la forma
        manejador.conversorNodoContainer(node, bucle);
        String idNodo = node.getAttribute("id");
        nodeMapFuncion.put(idNodo, bucle );
        bucle.setEditedText(node.getAttribute("nombre"));

        return bucle;

    }
	
	boolean esNodoDecision(String idNodo, List<String> nodosDecision) {
		boolean decision = false;
		if (nodosDecision.contains(idNodo)){
			decision = true;	
		}
		
		return decision;	
	}
	
	List<String> obtenerNodosTargetsDadoUnNodoOrigenDeDecision(String idNodoDecision, NodeList links) {
		
		List<String> nodosTarget = new ArrayList<String>();
		for (int i = 0; i < links.getLength(); ++i)
		{
			Element link = (Element)links.item(i);
			String idorigen = link.getAttribute("origin");
			if (idorigen.equals(idNodoDecision)) {
				nodosTarget.add(link.getAttribute("target"));
			}

		}
		
		return nodosTarget;
	}
	
	Document loadXmlFile(String filepath)
	{
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		factory.setIgnoringElementContentWhitespace(true);
		factory.setNamespaceAware(true);

		Document document = null;
		DocumentBuilder builder;
		try
		{
			File file = new File(filepath);
			builder = factory.newDocumentBuilder();
			document = builder.parse(file); 
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return document;
	}
}