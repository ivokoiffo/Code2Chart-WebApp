package com.grupo401.proyecto.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MainController {
	
	
	@RequestMapping(value={"/", "*"})
	public String viewHome() {
		return "index";
	}
	
	@RequestMapping(value="/generateDiagram", method=RequestMethod.POST)
	public ResponseEntity<String> generateDiagram(){
		
		//TODO hay que ver como vienen las cosas del front para llamar al Diagrama y devolverlo
		//en la entity
		//
		return new ResponseEntity<>("diagramCreado", HttpStatus.OK);
	}
}
