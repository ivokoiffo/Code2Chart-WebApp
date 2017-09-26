package com.grupo401.proyecto.diagram;

import org.w3c.dom.Element;

import com.mindfusion.diagramming.AnchorPattern;
import com.mindfusion.diagramming.ContainerNode;
import com.mindfusion.diagramming.Shape;
import com.mindfusion.diagramming.ShapeNode;
import com.mindfusion.diagramming.SimpleShape;

public class NodeHandler {
	
	public NodeHandler() {}
	public void conversor(Element nodo,ShapeNode diagramaNodo) {
		String tipo = nodo.getAttribute("tipo");
		switch (tipo) {
			 	case "proceso":
		        	diagramaNodo.setShape(Shape.fromId("Rectangle"));
		        break;
		        case "inicio":
		        	diagramaNodo.setShape(Shape.fromId("Start"));
		        break;
	
		        case "decisión":
	
		        	diagramaNodo.setShape(Shape.fromId("Decision"));
					diagramaNodo.setAnchorPattern(AnchorPattern.fromId("Decision2In2Out"));
					diagramaNodo.setTag(true);

			    break;
			    
		        case "fin":
		        	diagramaNodo.setShape(Shape.fromId("Terminator"));
		        break;
		        
		        case "entrada":
		        	diagramaNodo.setShape(Shape.fromId("Save"));
			    break;
			    
		        case "salida":
		        	diagramaNodo.setShape(Shape.fromId("Document"));
			    break;
		 } 
	}
	
	public void conversorNodoContainer(Element nodo, ContainerNode diagramaNodo) {
        String tipo = nodo.getAttribute("tipo");

        switch (tipo) {

            case "bucle":

                diagramaNodo.setShape(SimpleShape.Rectangle);

                break;
        }

    }
	
}