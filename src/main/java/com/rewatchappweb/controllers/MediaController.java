package com.rewatchappweb.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

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
}
