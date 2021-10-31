package com.rewatchappweb.controllers;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import com.rewatchappweb.errores.ErrorServicio;
import com.rewatchappweb.servicios.UsuarioServicio;
import com.rewatchappweb.utils.PeliculasAPI;

@Controller
@RequestMapping("/")
public class MainController {
	
	@Autowired
	private UsuarioServicio usuarioServicio;

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
		if(logout != null) {
			model.put("logout", "Ha salido correctamente.");
		}
		return "login.html";
	}
	
	@GetMapping("/registro")
	public String registro() {
		return "registro.html";
	}
	
	//Una vez iniciada la sesión--------------------------
	
	@Autowired
	private PeliculasAPI peliculasAPI;
	
	@PreAuthorize("hasAnyRole('ROLE_USER')")
	@GetMapping("/home")
	public String bienvenido(ModelMap model) {
		JSONObject object = peliculasAPI.buscarPorID();
		model.put("id", object.getString("imDbId"));
		model.put("titulo", object.getString("title"));
		model.put("year", object.getString("year"));
		model.put("rating", object.getString("imDb"));
		return "home.html";
	}
	
	@PreAuthorize("hasAnyRole('ROLE_USER')")
	@GetMapping("/peliculas")
	public String peliculas(/*ModelMap model*/) {
//		JSONObject object = peliculasAPI.buscarTop();
//		model.
		return "peliculas.html";
	}
	
	@PreAuthorize("hasAnyRole('ROLE_USER')")
	@GetMapping("/series")
	public String series() {
		return "series.html";
	}
	
	@PreAuthorize("hasAnyRole('ROLE_USER')")
	@GetMapping("/animes")
	public String animes() {
		return "animes.html";
	}
	
	//ACCIONES-----------------------------------------
	//No funciona la verificacion de la edad, si no se introduce un valor en el campo tira un error el servidor
	@PostMapping("/registrar")
	public String registrar(ModelMap modelo,
							@RequestParam String inputNombre, 
							@RequestParam Integer inputEdad,
							@RequestParam String inputEmail, 
							@RequestParam String inputPassword1, 
							@RequestParam String inputPassword2) {
		System.out.println(inputEdad);
		try {
			usuarioServicio.registrar(inputNombre, inputEdad, inputEmail, inputPassword1, inputPassword2);
		} catch (ErrorServicio e) {
			modelo.put("error", e.getMessage());
			modelo.put("nombre", inputNombre);
			modelo.put("edad", inputEdad);
			modelo.put("email", inputEmail);
			modelo.put("pass1", inputPassword1);
			modelo.put("pass2", inputPassword2);
			System.out.println(inputEdad);
			System.out.println("Error en mostrar(función /registrar)");
			return "registro.html";
		}
		return "index.html";
	}
	
	
	
	
	
	
	
}
