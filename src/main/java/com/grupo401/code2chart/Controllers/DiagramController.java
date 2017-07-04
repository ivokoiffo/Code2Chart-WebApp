package com.grupo401.code2chart.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class DiagramController {
	
	@RequestMapping(value="/cargar", method=RequestMethod.GET, produces = "application/json")
	public String cargarDiagrama(@RequestParam(value = "uri", required = true) String uri){
		//aca adentro hacen la fafa y devuelven la vista
		//ahora retorna string, despues para hacerlo civilizado
		//devolves ResponseEntity<String> que te permite 
		//devolver codigos http pero ahora no le den bola a eso
		//metanse con la biblio
		return null;
	}
	
	public String convertirDiagrama(){
		return null;
	}
	
	public String descargarDiagrama(){
		return null;
	}
	
	
}
