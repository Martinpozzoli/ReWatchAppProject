package com.rewatchappweb.servicios;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rewatchappweb.dao.UsuarioRepositorio;
import com.rewatchappweb.entidades.Usuario;

import com.rewatchappweb.errores.ErrorServicio;

@Service
public class UsuarioServicio {
	
	@Autowired
	private UsuarioRepositorio usuarioRepo;

	public void registrar(String nombre, Integer edad, String email, String password) throws ErrorServicio {
		
		validar(nombre,edad,email,password);
			
		Usuario usuario = new Usuario();
		usuario.setNombre(nombre);
		usuario.setEdad(edad);
		usuario.setEmail(email);
		usuario.setPassword(password);
		
		usuarioRepo.save(usuario);
	}
	
	public void modificar(Integer id, String nombre, Integer edad, String email, String password) throws ErrorServicio {
	
			validar(nombre,edad,email,password);
		
			Optional<Usuario> respuesta= usuarioRepo.findById(id);
			if (respuesta.isPresent()) {
				
				Usuario usuario = respuesta.get();
				
				usuario.setNombre(nombre);
				usuario.setEdad(edad);
				usuario.setEmail(email);
				usuario.setPassword(password);
				
				usuarioRepo.save(usuario);
			}else {
				throw new ErrorServicio("No se encontró el usuario con id= " + id);
			}	
	}
	
	private void validar(String nombre, Integer edad, String email, String password) throws ErrorServicio{
		
		if (nombre == null || nombre.isEmpty()) {
			throw new ErrorServicio("nombre sin especificar");
		}
		
		if (edad == null || edad.toString().isEmpty()) {
			throw new ErrorServicio("edad sin especificar");
		}
		
		if (email == null || email.isEmpty()) {
			throw new ErrorServicio("email sin especificar");
		}
		
		if (password == null || password.isEmpty()) {
			throw new ErrorServicio("contraseña sin especificar");
		}
		
	}
}
