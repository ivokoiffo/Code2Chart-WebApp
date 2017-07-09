package com.grupo401.code2chart.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MainController {
	
	@RequestMapping(value="/",method = RequestMethod.GET)
	public String homepage(){
		return "index";
	}
	@RequestMapping(method = RequestMethod.GET)
	public String redireccion(){
		return "redirect:/";
	}
}
