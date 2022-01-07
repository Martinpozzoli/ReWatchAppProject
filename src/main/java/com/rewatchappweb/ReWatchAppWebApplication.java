package com.rewatchappweb;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.rewatchappweb.repositories.UserRepository;
import com.rewatchappweb.services.MediaAPIService;
import com.rewatchappweb.services.MediaService;
import com.rewatchappweb.services.UserService;
import com.rewatchappweb.utils.MediaAPI;





@SpringBootApplication
@ComponentScan({"com.rewatchappweb"})
@EntityScan("com.rewatchappweb")
@EnableJpaRepositories("com.rewatchappweb")
public class ReWatchAppWebApplication implements CommandLineRunner{
	
	@Autowired
	MediaAPIService mediaAPIService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	MediaService mediaService;
	
	@Autowired
	UserRepository userRepo;
	
	@Autowired
	MediaAPI mediaAPI;
	
	
	public static void main(String[] args) {
		SpringApplication.run(ReWatchAppWebApplication.class, args);
		
	}

	@Override
	public void run(String... args) throws Exception {
		
//		Media m1 = mediaAPIService.generateMedia("tt0411008");
//		System.out.println(m1.toString());
//		
//		userService.register("Martin", "Pozzoli", LocalDate.now(), "martin@hotmail.com", "asd", "asd");
//		User u1 = userRepo.findByEmail("martin@hotmail.com");
//		
//		mediaService.addToList(u1.getId(), "tt0411008", "alreadySeenList");
//		System.out.println("Listo!----------");
//		u1 = userRepo.findByEmail("martin@hotmail.com");
//		System.out.println(u1.toString());
//		System.out.println("lista: " + u1.getAlreadySeenList().toString());
//		
//		Media m2 = mediaService.findMedia("tt0411008");
//		System.out.println(m2.toString());
		
//		mediaAPIService.generateMediaByList("comingSoonTV");
		

		
		
	}
}
