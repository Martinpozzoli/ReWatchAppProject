package com.rewatchappweb.controllers;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import com.rewatchappweb.errors.ErrorServicio;
import com.rewatchappweb.services.UserService;

@Controller
@RequestMapping("/")
public class MainController {
	
	@Autowired
	private UserService userService;

	//DIRECCIONES PRINCIPALES-----------------------------
	
	@GetMapping("/")
	public String index() {
		return "index.html";
	}
	
	@GetMapping("/login")
	public String login(@RequestParam(required = false) String error, 
						@RequestParam(required = false) String logout,
						ModelMap model) {
		if(error != null) {
			model.put("error", "Dirección de correo o contraseña incorrectos.");
		}
		return "login.html";
	}
	
	@GetMapping("/registro")
	public String registro() {
		return "registro.html";
	}
	
	//ACCIONES----------------------------------------------
	@PostMapping("/registrar")
	public String registrar(ModelMap modelo,
							@RequestParam @Nullable String inputFirstName, 
							@RequestParam @Nullable String inputLastName, 
							@RequestParam @Nullable String inputBirthDate,
							@RequestParam @Nullable String inputEmail, 
							@RequestParam @Nullable String inputPassword1, 
							@RequestParam @Nullable String inputPassword2) {
		LocalDate localBirthDate;
		try {
			localBirthDate = LocalDate.parse(inputBirthDate);
		}catch(Exception e) {
			localBirthDate = LocalDate.parse("1000-01-01");
			e.printStackTrace();
		}
		try {
			userService.register(inputFirstName, inputLastName, localBirthDate, inputEmail, inputPassword1, inputPassword2);
		} catch (ErrorServicio e) {
			modelo.put("error", e.getMessage());
			modelo.put("nombre", inputFirstName);
			modelo.put("apellido", inputLastName);
			modelo.put("nacimiento", localBirthDate);
			modelo.put("email", inputEmail);
			modelo.put("pass1", inputPassword1);
			modelo.put("pass2", inputPassword2);
			System.out.println(e.getMessage());
			return "registro.html";
		}
		return "index.html";
	}
	
	
	
	
	
	
	
}
