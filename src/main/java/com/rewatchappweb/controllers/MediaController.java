package com.rewatchappweb.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.rewatchappweb.entities.Media;
import com.rewatchappweb.entities.User;
import com.rewatchappweb.errors.ErrorServicio;
import com.rewatchappweb.services.MediaService;
import com.rewatchappweb.services.UserMediaService;


@Controller
@RequestMapping("/media")
public class MediaController {

	@Autowired
	private MediaService mediaService;
	
	@Autowired
	private UserMediaService userMediaService;
	
	@GetMapping(value = "/details/{id}")
	public String mediaDetails(@PathVariable String id, ModelMap model) {
		Media m = mediaService.findMedia(id);
		
		model.addAttribute("media", m);
		return "details.html";
	}
	
	@PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMIN')")
	@GetMapping("/add/{mediaId}/{userId}/{listName}")
	public String addToUserList(HttpSession session, 
									@PathVariable String mediaId, 
									@PathVariable String userId, 
									@PathVariable String listName, 
									ModelMap model) throws ErrorServicio{
		User u = (User) session.getAttribute("usersession");
		if(u == null || !u.getId().equals(userId)) {
			return "redirect:/home";
		}
		try {
			userMediaService.addToList(userId, mediaId, listName);
			model.put("success", "agregada exitosamente");
			return "redirect:/user/" + userId;	
		}catch(ErrorServicio e) {
			System.out.println(e);
			model.put("error", e.getMessage());
			System.out.println(userId + " no se pudo che, catch");
			return mediaDetails(mediaId, model);
		}
			
	}
	
	@PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMIN')")
	@GetMapping("/remove/{mediaId}/{userId}/{listName}")
	public String removeFromUserList(HttpSession session, 
										@PathVariable String mediaId, 
										@PathVariable String userId, 
										@PathVariable String listName, 
										ModelMap model) throws ErrorServicio{
		User u = (User) session.getAttribute("usersession");
		if(u == null || !u.getId().equals(userId)) {
			return "redirect:/home";
		}
		try {
			userMediaService.removeFromList(userId, mediaId, listName);
			model.put("success", "eliminada exitosamente");
			return "redirect:/user/" + userId;	
		}catch(ErrorServicio e) {
			System.out.println(e);
			model.put("error", e.getMessage());
			System.out.println(userId + " no se pudo che, catch");
			return mediaDetails(mediaId, model);
		}
			
	}
	
	@PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMIN')")
	@PostMapping(value = "/browse")
	public String mediaSearch(Model model, @RequestParam @Nullable String p) {
		if (p == null || p.equals("")) {
			String error = "Por favor ingrese el titulo, actor o director que busca";
			System.out.println(error);
			model.addAttribute("error", error);			
		}else {
			try {
				
				model.addAttribute("mediaList", mediaService.searchByComparison(p));
				System.out.println(mediaService.searchByComparison(p).toString());
				
				if(mediaService.searchByComparison(p).isEmpty()) {
					String error = "No se encontraron peliculas que coincidan con la búsqueda";
					System.out.println(error);
					model.addAttribute("error", error);	
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return "browse.html";
	}
}
