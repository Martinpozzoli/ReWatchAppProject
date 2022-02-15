package com.rewatchappweb.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rewatchappweb.entities.Media;
import com.rewatchappweb.entities.User;
import com.rewatchappweb.errors.ErrorServicio;
import com.rewatchappweb.repositories.UserRepository;

@Service
public class UserMediaService {

	@Autowired
	private MediaAPIService mediaAPIService;

	@Autowired
	private MediaService mediaService;

	@Autowired
	private UserRepository userRepo;

	// Toma una lista de un usuario y genera sublistas para mostrar todo su
	// contenido en userlist.html
	public List<Media> userListGetter(String userId, String listName, Integer numPage) {
		User activeUser = userRepo.getById(userId);
		ArrayList<String> mediaIds = new ArrayList<String>();
		List<Media> userList = new ArrayList<Media>();

		switch (listName) {
		case "favoritesList":
			mediaIds = activeUser.getFavoritesList();
			break;
		case "waitingList":
			mediaIds = activeUser.getWaitingList();
			break;
		case "alreadySeenList":
			mediaIds = activeUser.getAlreadySeenList();
			break;
		}
		List<String> mediaIdsSublist = new ArrayList<String>();
		Integer firstIndex = 0;
		Integer lastIndex = 12;
		if (numPage == 1 && mediaIds.size() > 1) {
			firstIndex = 0;
			if (mediaIds.size() > 12) {
				lastIndex = 11;
			} else {
				lastIndex = mediaIds.size() - 1;
			}

			mediaIdsSublist = mediaIds.subList(firstIndex, lastIndex + 1);
			userList = mediaAPIService.generateUserMediaByList(mediaIdsSublist);
		} else if ((numPage > 1) && (mediaIds.size() > (12 * (numPage - 1) - 1))) {
			firstIndex = 12 * (numPage - 1);
			if (mediaIds.size() > (12 * numPage)) {
				lastIndex = 12 * (numPage) - 1;
			} else {
				lastIndex = mediaIds.size() - 1;
			}

			mediaIdsSublist = mediaIds.subList(firstIndex, lastIndex + 1);
			userList = mediaAPIService.generateUserMediaByList(mediaIdsSublist);

		} else if (numPage > (mediaIds.size() / 12)) {
			userList = null;
		}

		return userList;
	}

	// Tomo una media, usuario y lista para agregar la pelicula en la lista
	// correspondiente del usuario.
	public void addToList(String userId, String mediaId, String listName) throws ErrorServicio {

		User u = new User();
		try {
			Optional<User> us = userRepo.findById(userId);
			if (us.isPresent()) {
				u = us.get();
			} else {
				System.out.println("No se encontró el usuario");
				throw new ErrorServicio("No se encontró el usuario");
			}
			
			if (mediaService.findMedia(mediaId) != null) {
				switch (listName) {
				case "favoritesList":
					if (!u.getFavoritesList().contains(mediaId)) {
						u.getFavoritesList().add(mediaId);
					} else {
						throw new ErrorServicio("Ya estaba agregada");
					}
					break;

				case "waitingList":
					if (!u.getWaitingList().contains(mediaId)) {
						u.getWaitingList().add(mediaId);
					} else {
						throw new ErrorServicio("Ya estaba agregada");
					}
					break;

				case "alreadySeenList":
					if (!u.getAlreadySeenList().contains(mediaId)) {
						u.getAlreadySeenList().add(mediaId);
					} else {
						throw new ErrorServicio("Ya estaba agregada");
					}
					break;
				}
				userRepo.save(u);
			} else {
				throw new ErrorServicio("No se encontró la media con id = " + mediaId);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// Tomo una media, usuario y lista para eliminar la pelicula/serie en la lista
	// correspondiente del usuario.
	public void removeFromList(String userId, String mediaId, String listName) throws ErrorServicio {

		User u = new User();
		try {
			Optional<User> us = userRepo.findById(userId);
			if (us.isPresent()) {
				u = us.get();
			} else {
				throw new ErrorServicio("No se encontró el usuario");
			}

			switch (listName) {
			case "favoritesList":
				if (u.getFavoritesList().contains(mediaId)) {
					u.getFavoritesList().remove(mediaId);
				} else {
					throw new ErrorServicio("No se encontró la media con id = " + mediaId + " en la lista.");
				}
				break;

			case "waitingList":
				if (u.getWaitingList().contains(mediaId)) {
					u.getWaitingList().remove(mediaId);
				} else {
					throw new ErrorServicio("No se encontró la media con id = " + mediaId + " en la lista.");
				}
				break;

			case "alreadySeenList":
				if (u.getAlreadySeenList().contains(mediaId)) {
					u.getAlreadySeenList().remove(mediaId);
				} else {
					throw new ErrorServicio("No se encontró la media con id = " + mediaId + " en la lista.");
				}
				break;
			}
			userRepo.save(u);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
