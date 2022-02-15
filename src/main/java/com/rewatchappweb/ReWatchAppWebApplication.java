package com.rewatchappweb;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.rewatchappweb.repositories.MediaListsRepository;
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
	MediaListsRepository mediaListsRepo;
	
	@Autowired
	MediaAPI mediaAPI;
	
	
	public static void main(String[] args) {
		SpringApplication.run(ReWatchAppWebApplication.class, args);
		
	}

	@Override
	public void run(String... args) throws Exception {
		

//		MediaLists mLists = mediaListsRepo.getById(1);
//		System.out.println(LocalDate.now().toString());
//		mLists.setLastRefreshDate(LocalDate.now());
//		mediaListsRepo.save(mLists);
		mediaService.updateMediaLists();
		
		
		
	}
}
