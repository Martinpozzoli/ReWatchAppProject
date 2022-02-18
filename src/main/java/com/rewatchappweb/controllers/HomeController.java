package com.rewatchappweb.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.rewatchappweb.entities.Media;
import com.rewatchappweb.entities.MediaLists;
import com.rewatchappweb.services.MediaService;

@Controller
@RequestMapping("/home")
public class HomeController {
	
	@Autowired
	private MediaService mediaService;

	@PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMIN')")
	@GetMapping(value = "")
	public String contenedorPrincipal(Model model) {
		try {
			MediaLists mediaLists = mediaService.updateMediaLists();
			
			//List<Media> comingSoonMovies = mediaService.listForController("comingSoonMovies", mediaLists);
//			List<Media> comingSoonSeries = mediaService.listForController("comingSoonSeries", mediaLists);
			List<Media> popularMovies = mediaService.listForController("popularMovies", mediaLists);
//			List<Media> popularSeries = mediaService.listForController("popularSeries", mediaLists);
			List<Media> bestMovies = mediaService.listForController("bestMovies", mediaLists);
//			List<Media> bestSeries = mediaService.listForController("bestSeries", mediaLists);
			
			List<Media> moviesForCarousel = popularMovies.subList(0, 4);
			model.addAttribute("moviesForCarousel", moviesForCarousel);
		
			//List<Media> comingSoonMoviesSub = comingSoonMovies.subList(0, 15);
			List<Media> popularMoviesSub = popularMovies.subList(0, 15);
			List<Media> bestMoviesSub = bestMovies.subList(0, 15);
			
			//model.addAttribute("comingSoonMovies", comingSoonMoviesSub);
//			model.addAttribute("comingSoonSeries", comingSoonSeries);
			model.addAttribute("popularMovies", popularMoviesSub);
//			model.addAttribute("popularSeries", popularSeries);
			model.addAttribute("bestMovies", bestMoviesSub);
//			model.addAttribute("bestSeries", bestSeries);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "home.html";
	}
}
