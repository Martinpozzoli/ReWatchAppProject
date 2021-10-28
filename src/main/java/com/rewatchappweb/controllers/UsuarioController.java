package com.rewatchappweb.controllers;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.rewatchappweb.dao.IUserRepository;
import com.rewatchappweb.entidades.Usuario;


@RestController
public class UsuarioController {
	
	@Autowired
	private IUserRepository uDAO; //En caso de error probar con alternar con IUsuarioDAO en lugar de IUserRepository
	
//	@Autowired
//	private JWTUtil jwtutil;
	
	@PersistenceContext
    EntityManager entityManager;
	//PRUEBA1:
	@RequestMapping(value = "api/usuario/{id}")
	public Usuario getUsuario(@PathVariable Integer id) {
		Usuario usuario = new Usuario();
		usuario.setId(id);
		usuario.setNombre("Martin");
		usuario.setEdad(25);
		usuario.setEmail("martin@gmail.com");
		usuario.setPassword("1234");
		return usuario;
	}
	//PRUEBA2 ListarUsuarios:
	@RequestMapping(value = "api/usuarios")
	public List<Usuario> getUsuarios() {
		return (List<Usuario>) uDAO.findAll();
	}
	//REGISTRO:
	@RequestMapping(value = "api/usuarios", method = RequestMethod.POST)
	public void registrarUsuario(@RequestBody Usuario u) {
//		if (u.getNombre() == null || u.getNombre().isEmpty()) {
//			throw new ErrorServicio("El nombre no puede ser nulo");
//		}
		uDAO.save(u);
	}
	//LOG IN:
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "api/login", method = RequestMethod.POST)
	public String loginUsuario(@RequestBody Usuario usuario) {
		String query = "FROM Usuario Where email = :email AND password = :password";
		List<Usuario> lista = entityManager.createQuery(query)
				.setParameter("email", usuario.getEmail())
				.setParameter("password", usuario.getPassword())
				.getResultList();
		if (lista.isEmpty()) {
			return "FAIL";
		}else {
			//Para validar y mantener iniciada la sesión:
//			Usuario u = lista.get(0);
//			String tokenJwt = jwtutil.create(String.valueOf(u.getId()), u.getEmail());
//			return tokenJwt;
			return "OK";
			
			
		}
	}
	
	@RequestMapping(value = "usuario1535")
	public Usuario editar() {
		Usuario usuario = new Usuario();
		usuario.setNombre("Martin");
		usuario.setEdad(25);
		usuario.setEmail("martin@gmail.com");
		usuario.setPassword("1234");
		return usuario;
	}
	
	@RequestMapping(value = "usuario456")
	public Usuario eliminar() {
		Usuario usuario = new Usuario();
		usuario.setNombre("Martin");
		usuario.setEdad(25);
		usuario.setEmail("martin@gmail.com");
		usuario.setPassword("1234");
		return usuario;
	}
	
	@RequestMapping(value = "usuario896")
	public Usuario buscar() {
		Usuario usuario = new Usuario();
		usuario.setNombre("Martin");
		usuario.setEdad(25);
		usuario.setEmail("martin@gmail.com");
		usuario.setPassword("1234");
		return usuario;
	}
	
}
