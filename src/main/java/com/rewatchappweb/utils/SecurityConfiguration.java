package com.rewatchappweb.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.password.NoOpPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
//@EnableWebSecurity
public class SecurityConfiguration /*extends WebSecurityConfigurerAdapter*/{
//
//	@Autowired
//	UserDetailsService userDetailsService;
//	
//	@Override
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		auth.userDetailsService(userDetailsService);
//		
//	}
	//Es necesario crear un Encoder para las contraseñas
	//Es importante trabajar con Hashed passwords para mejorar la seguridad
	//NoOpPasswordEncoder NUNCA DEBE SER USADO, en este caso es usado para facilitar las pruebas  de la app
//	@Bean
//	public PasswordEncoder getPasswordEncoder() {
//		return NoOpPasswordEncoder.getInstance();
//	}
//	
//	C
//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//		http.authorizeRequests()
//				.antMatchers("/admin.html").hasRole("ADMIN")
//				.antMatchers("/bienvenido.html").hasAnyRole("USER","ADMIN")
//				.antMatchers("/").permitAll()
//				.and().formLogin()
//				.loginPage("/login.html");
//				.loginProcessingUrl("/perform_login")
//			    .defaultSuccessUrl("/bienvenido.html", true)
		//https://www.baeldung.com/spring-security-login
//				
//				
//	}

	
	
}
