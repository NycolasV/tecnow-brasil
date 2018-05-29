package br.com.tecnow.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 
 * @author Nycolas de L. Vieira
 */

@Controller
public class IndexController {
	
	@RequestMapping("/")
	public String abrirIndex() {
		return "index";
	}
	
	@RequestMapping("/404")
	public String abrir404() {
		return "404";
	}
	
}
