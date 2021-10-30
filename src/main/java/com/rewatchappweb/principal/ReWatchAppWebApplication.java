package com.rewatchappweb.principal;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication
@ComponentScan({"com.rewatchappweb"})
@EntityScan("com.rewatchappweb")
@EnableJpaRepositories("com.rewatchappweb")
public class ReWatchAppWebApplication{
	
	public static void main(String[] args) {
		SpringApplication.run(ReWatchAppWebApplication.class, args);
	}
}
