package com.grupo401.code2chart.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {
	@RequestMapping({
		"/",
		//aca definen las rutas validas que van a querer que el 
		//coso este tome todo lo que sea distinto de esto va
		//al metodo de abajo que redireccion al home
	})
	public String homepage(){
		return "forward:/index.html";
	}

}
