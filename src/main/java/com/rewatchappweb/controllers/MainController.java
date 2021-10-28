package com.rewatchappweb.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/")
public class MainController {

	//DIRECCIONES PRINCIPALES-----------------------------
	
	@GetMapping("/")
	public String index() {
		return "index.html";
	}
	
	@GetMapping("/login")
	public String login() {
		return "login.html";
	}
	
	@GetMapping("/registro")
	public String registro() {
		return "registro.html";
	}
	
	//ACCIONES------------------------------------
	
	@PostMapping("/registrar")
	public String registrar(@RequestParam String inputNombre, 
							@RequestParam Integer inputEdad,
							@RequestParam String inputEmail, 
							@RequestParam String inputPassword1, 
							@RequestParam String inputPassword2) {
		System.out.println(inputNombre + inputEdad + inputEmail + inputPassword1 + inputPassword2);
		return "registro.html";
	}
	
	
	
	
	
	
	
}
