package com.rewatchappweb.controllers;

import java.util.List;

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
import com.rewatchappweb.repositories.UserRepository;
import com.rewatchappweb.services.MediaAPIService;
import com.rewatchappweb.services.UserMediaService;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private MediaAPIService mediaAPIService;
	
	@Autowired
	private UserMediaService userMediaService;
	
	@Autowired
	private UserRepository userRepo;
	
	@PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMIN')")
	@GetMapping(value = "/{userId}")
	public String userDetails(HttpSession session, 
												@PathVariable String userId, 
												ModelMap model) {
		
		User u = (User) session.getAttribute("usersession");
		if(u == null || !u.getId().equals(userId)) {
			return "redirect:/home";
		}else {
			User activeUser = userRepo.getById(userId);
			model.addAttribute("user", activeUser);
			
			List<Media> favoritesList = mediaAPIService.generateUserMediaByList(activeUser.getFavoritesList());
			List<Media> alreadySeenList = mediaAPIService.generateUserMediaByList(activeUser.getAlreadySeenList());
			List<Media> waitingList = mediaAPIService.generateUserMediaByList(activeUser.getWaitingList());
			
			model.addAttribute("favoritesList", favoritesList);
			model.addAttribute("alreadySeenList", alreadySeenList);
			model.addAttribute("waitingList", waitingList);
			
			return "userdetails.html";
		}
	}
	
	@PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMIN')")
	@GetMapping(value = "/{userId}/{listName}/{numPage}")
	public String userListPages(HttpSession session, 
													@PathVariable String userId, 
													@PathVariable String listName,
													@PathVariable Integer numPage,
													ModelMap model) {
		User u = (User) session.getAttribute("usersession");
		if(u == null || !u.getId().equals(userId)) {
			return "redirect:/home";
		}else {
			User activeUser = userRepo.getById(userId);
			model.addAttribute("user", activeUser);
			
			List<Media> mediaList = userMediaService.userListGetter(userId, listName, numPage);
			boolean lastPage = false;
			if(userMediaService.userListGetter(userId, listName, numPage + 1) == null) {
				lastPage = true;
			}
			model.addAttribute("mediaList", mediaList);
			model.addAttribute("numPage", numPage);
			model.addAttribute("lastPage", lastPage);
			model.addAttribute("listName", listName);
			
			return "userlist.html";
		}
	}
}
