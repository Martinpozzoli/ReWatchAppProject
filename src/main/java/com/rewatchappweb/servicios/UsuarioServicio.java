package com.rewatchappweb.servicios;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.rewatchappweb.dao.UsuarioRepositorio;
import com.rewatchappweb.entidades.Usuario;

import com.rewatchappweb.errores.ErrorServicio;

@Service
public class UsuarioServicio implements UserDetailsService{
	
	@Autowired
	private UsuarioRepositorio usuarioRepo;
	
//	@Autowired
//	private NotificacionServicio notificacionServicio;

	@Transactional
	public void registrar(String nombre, Integer edad, String email, String password1, String password2) throws ErrorServicio {
		
		
		validar(nombre,edad,email,password1,password2);
			
		Usuario usuario = new Usuario();
		usuario.setNombre(nombre);
		usuario.setEdad(edad);
		usuario.setEmail(email);
		String encriptada = new BCryptPasswordEncoder().encode(password1);
		usuario.setPassword(encriptada);
		
		usuarioRepo.save(usuario);
		
		//notificacionServicio.enviar("Bienvenido a la comunidad! Esperamos que disfrutes", "Bienvenid@ a ReWatchApp", usuario.getEmail());
	}
	
	@Transactional
	public void modificar(Integer id, String nombre, Integer edad, String email, String password1, String password2) throws ErrorServicio {
	
			validar(nombre,edad,email,password1,password2);
		
			Optional<Usuario> respuesta= usuarioRepo.findById(id);
			if (respuesta.isPresent()) {
				
				Usuario usuario = respuesta.get();
				
				usuario.setNombre(nombre);
				usuario.setEdad(edad);
				usuario.setEmail(email);
				String encriptada = new BCryptPasswordEncoder().encode(password1);
				usuario.setPassword(encriptada);
				
				usuarioRepo.save(usuario);
			}else {
				throw new ErrorServicio("No se encontró el usuario con id= " + id);
			}	
	}
	
	private void validar(String nombre, Integer edad, String email, String password1, String password2) throws ErrorServicio{
		
		if (nombre == null || nombre.isEmpty()) {
			throw new ErrorServicio("Nombre sin especificar");
		}
		
		if (String.valueOf(edad).isBlank() || edad.toString().isEmpty()) {
			System.out.println(edad);
			System.out.println("Error en registrar");
			throw new ErrorServicio("Edad sin especificar");
		}
		
		if (email == null || email.isEmpty()) {
			throw new ErrorServicio("Email sin especificar");
		}
		
		if (password1 == null || password1.isEmpty()) {
			throw new ErrorServicio("Contraseña sin especificar");
		}
		
		if (!password1.equals(password2)) {
			throw new ErrorServicio("No coinciden las contraseñas");
		}
		
	}
	//SEGURIDAD----------------------------------------
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Usuario usuario = usuarioRepo.findByEmail(email);
		if (usuario != null) {
			List<GrantedAuthority> permisos = new ArrayList<GrantedAuthority>();
			GrantedAuthority p1 = new SimpleGrantedAuthority("MODULO_MEDIA");
			GrantedAuthority p2 = new SimpleGrantedAuthority("MODULO_BIBLIOTECA");
			permisos.add(p1);
			permisos.add(p2);
			User user = new User(usuario.getEmail(), usuario.getPassword(), permisos);
			return user;
		} else {
			return null;
		}
	}
}
