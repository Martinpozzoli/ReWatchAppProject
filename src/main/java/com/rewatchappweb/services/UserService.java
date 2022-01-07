package com.rewatchappweb.services;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.rewatchappweb.entities.Role;
import com.rewatchappweb.entities.User;
import com.rewatchappweb.errors.ErrorServicio;
import com.rewatchappweb.repositories.UserRepository;

@Service
public class UserService implements UserDetailsService{
	
	@Autowired
	private UserRepository userRepo;
	
//	@Autowired
//	private NotificacionServicio notificacionServicio;

	@Transactional
	public void register(String firstname, String lastname, LocalDate birthdate, String email, String password1, String password2) throws ErrorServicio {
		
		
		boolean valid = validate(firstname,lastname,birthdate,email,password1,password2);
		if(valid == true) {
			User user = new User();
			user.setFirstname(firstname);
			user.setLastname(lastname);
			user.setBirthdate(birthdate);
			user.setEmail(email);
			String encriptada = new BCryptPasswordEncoder().encode(password1);
			user.setPassword(encriptada);
		
			userRepo.save(user);
		
			//notificacionServicio.enviar("Bienvenido a la comunidad! Esperamos que disfrutes", "Bienvenid@ a ReWatchApp", usuario.getEmail());
		}
	}
	
	@Transactional
	public void modify(String id, String firstname, String lastname, LocalDate birthdate, String email, 
			String password1, String password2, ArrayList<String> favoritesList, ArrayList<String> waitingList, 
			ArrayList<String> alreadySeenList) throws ErrorServicio {
	
			validate(firstname,lastname,birthdate,email,password1,password2);
		
			Optional<User> response = userRepo.findById(id);
			if (response.isPresent()) {
				
				User user = response.get();
				
				user.setFirstname(firstname);
				user.setLastname(lastname);
				user.setBirthdate(birthdate);
				user.setEmail(email);
				String encryptedPass = new BCryptPasswordEncoder().encode(password1);
				user.setPassword(encryptedPass);
				
				userRepo.save(user);
			}else {
				throw new ErrorServicio("No se encontró el usuario con id= " + id);
			}	
	}
	
	private boolean validate(String firstname, String lastname, LocalDate birthdate, String email, String password1, String password2) throws ErrorServicio{
		
		boolean valid = true;
		
		if (firstname == null || firstname.isEmpty()) {
			throw new ErrorServicio("Nombre sin especificar");
		}
		
		if (lastname == null || lastname.isEmpty()) {
			throw new ErrorServicio("Apellido sin especificar");
		}
		
		if(ChronoUnit.YEARS.between(birthdate, LocalDate.now()) > 140) {
			throw new ErrorServicio("Ingrese una fecha de nacimiento válida");
		}
		
		if (email == null || email.isEmpty()) {
			throw new ErrorServicio("Email sin especificar");
		}
		
		try {
			Optional<User> u = Optional.of(userRepo.findByEmail(email));
			if(!u.equals(null)) {
				valid = false;
			}
		}catch (Exception e){
			
		}
		if(valid == false) {
			throw new ErrorServicio("El correo ingresado ya esta en uso");
		}
		
		if (password1 == null || password1.isEmpty()) {
			throw new ErrorServicio("Contraseña sin especificar");
		}
		
		if (!password1.equals(password2)) {
			throw new ErrorServicio("No coinciden las contraseñas");
		}
		
		return valid;
		
	}
	//SEGURIDAD----------------------------------------
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User user = userRepo.findByEmail(email);
		if (user != null) {
			List<GrantedAuthority> license = new ArrayList<GrantedAuthority>();
			GrantedAuthority p1 = new SimpleGrantedAuthority("ROLE_"+Role.USER.name());
			GrantedAuthority p2 = new SimpleGrantedAuthority("ROLE_"+Role.ADMIN.name());
			if(user.getRole() == null || user.getRole().equals(Role.USER)) {
				license.add(p1);
			}else if(user.getRole().equals(Role.ADMIN)) {
				license.add(p2);
			}
			//Se guardan los datos de la sesión para que puedan ser utilizados:
			ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
			HttpSession session = attr.getRequest().getSession(true);
			session.setAttribute("usersession", user);
			
			org.springframework.security.core.userdetails.User userU;
			userU = new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), license);
			return userU;
		} else {
			return null;
		}
	}
}
