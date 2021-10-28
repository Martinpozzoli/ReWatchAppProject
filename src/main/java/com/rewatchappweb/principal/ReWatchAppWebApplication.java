package com.rewatchappweb.principal;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.rewatchappweb.dao.IMediaDAO;
import com.rewatchappweb.dao.IUsuarioDAO;

@SpringBootApplication
@ComponentScan({"com.rewatchappweb"})
@EntityScan("com.rewatchappweb")
@EnableJpaRepositories("com.rewatchappweb")
public class ReWatchAppWebApplication implements CommandLineRunner{

	@Autowired
	IUsuarioDAO uDAO;
	IMediaDAO mDAO;
	public static void main(String[] args) {
		SpringApplication.run(ReWatchAppWebApplication.class, args);
		
	}

	@Override
	public void run(String... args) throws Exception {
		
//		Usuario u1 = new Usuario("Carlos", 28, "carlos@hotmail.com", "1234", true, "USER");
//		
//		Media m1 = new Media(1,"Harry Potter", 2005);
//		Media m2 = new Media(2,"Narnia", 2008);
//		Media m3 = new Media(3,"El Marginal", 2017);
//		
//		
//		Set<Media> media = u1.getMediaList();
//		media.add(m1);
//		media.add(m2);
//		media.add(m3);
//		
//		u1.agregarMedia(m1);
//		u1.agregarMedia(m2);
//		u1.agregarMedia(m3);
//		
//		Usuario u2 = new Usuario("Pepe", 56, "elpepe@hotmail.com", "qwertyuiop", true, "ADMIN");
//		
//		Set<Media> media2 = u2.getMediaList();
//		media2.add(m2);
//		media2.add(m3);
//		
//		u2.agregarMedia(m2);
//		u2.agregarMedia(m3);
//		
//		uDAO.save(u1);
//		uDAO.save(u2);
	}
}
