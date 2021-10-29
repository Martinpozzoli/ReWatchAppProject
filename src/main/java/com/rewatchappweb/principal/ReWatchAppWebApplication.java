package com.rewatchappweb.principal;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.rewatchappweb.servicios.UsuarioServicio;

@SpringBootApplication
@ComponentScan({"com.rewatchappweb"})
@EntityScan("com.rewatchappweb")
@EnableJpaRepositories("com.rewatchappweb")
public class ReWatchAppWebApplication extends WebSecurityConfigurerAdapter{
	
	
	  @Override
	    protected void configure(HttpSecurity security) throws Exception
	    {
		 //para desactivar el login obligatorio de momento
	     security.httpBasic().disable();
	     //para evitar errores en la redireccion a distintas url sin permisos
	     security.csrf().disable();
	    }

	@Autowired
	private UsuarioServicio usuarioServicio;
	
	public static void main(String[] args) {
		SpringApplication.run(ReWatchAppWebApplication.class, args);
	}
	
	public void ConfigureGlobal(AuthenticationManagerBuilder auth) throws Exception{
		auth.userDetailsService(usuarioServicio).passwordEncoder(new BCryptPasswordEncoder());
	}
}
