package com.rewatchappweb.services;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rewatchappweb.entities.Media;
import com.rewatchappweb.entities.MediaLists;
import com.rewatchappweb.entities.User;
import com.rewatchappweb.repositories.MediaRepository;
import com.rewatchappweb.repositories.UserRepository;
import com.rewatchappweb.utils.MediaAPI;

@Service
public class MediaService {

	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private MediaRepository mediaRepo;
	
	@Autowired
	private MediaAPIService mediaApiService;
	
	//Tomo una media, usuario y lista para agregar la pelicula en la lista correspondiente del usuario.
	public void addToList(String userId, String mediaId, String listName) {
		
		User u = new User();
		try {
			Optional<User> us = userRepo.findById(userId);
			if(us.isPresent()) {
				u = us.get();
			}else {
				System.out.println("No se encontró el usuario");
			}
		
		Media m = findMedia(mediaId);
//		System.out.println("Media en addToList = " + m.toString());
		switch(listName) {
		case "favoritesList":	u.getFavoritesList().add(m.getId());
								break;
								
		case "waitingList":		u.getWaitingList().add(m.getId());
								break;
								
		case "alreadySeenList":	u.getAlreadySeenList().add(m.getId());
								break;		
		}
		userRepo.save(u);
		
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	//Busca en la base de datos y si no se encuentra, se busca a traves de la api de imdb
	public Media findMedia(String id) {
		
		Media m = new Media();
		try {
			Optional<Media> md = mediaRepo.findById(id);
			if (md.isPresent()) {
				m = md.get();
				System.out.println("Hallado en DB");
			}else {
				m = mediaApiService.generateMedia(id);
				System.out.println("Hallado en API");
				addMediaToDB(m);
			}
			return m;
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	//Agregar media a la base de datos para evitar el exceso de consultas a las APIs que son limitadas:
	public void addMediaToDB(Media m) {
		try {
		if(!mediaRepo.findById(m.getId()).isPresent()) {
			mediaRepo.save(m);
			System.out.println("El registro con id = " + m.getId() + " ha sido registrado en la base de datos exitosamente");
		}else {
			System.out.println("El registro con id = " + m.getId() + " ya se encuentra registrado en la base de datos");
		}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//Actualización de listas para mostrar en Home, Peliculas y Series:
	@Autowired
	private MediaAPI mediaApi;
	
	public MediaLists updateMediaLists() {
		ArrayList<String> comingSoonMovies = new ArrayList<String>();
		ArrayList<String> comingSoonSeries = new ArrayList<String>();
		ArrayList<String> popularMovies = new ArrayList<String>();
		ArrayList<String> popularSeries = new ArrayList<String>();
		ArrayList<String> bestMovies = new ArrayList<String>();
		ArrayList<String> bestSeries = new ArrayList<String>();
		
		try {
			comingSoonMovies = mediaApi.selectedAPIList("comingSoonMovies");
			comingSoonSeries = mediaApi.selectedAPIList("comingSoonTV");
			popularMovies = mediaApi.selectedAPIList("popularMovies");
			popularSeries = mediaApi.selectedAPIList("popularTV");
			bestMovies = mediaApi.selectedAPIList("topRatedMovies");
			bestSeries = mediaApi.selectedAPIList("topRatedTV");
		}catch (Exception e) {
			e.printStackTrace();
		}
		MediaLists mediaLists = new MediaLists();
		
		mediaLists.setComingSoonMovies(comingSoonMovies);
		mediaLists.setComingSoonSeries(comingSoonSeries);
		mediaLists.setPopularMovies(popularMovies);
		mediaLists.setPopularSeries(popularSeries);
		mediaLists.setBestMovies(bestMovies);
		mediaLists.setBestSeries(bestSeries);
		
		return(mediaLists);
	}
	
	//Devolver las listas anteriores para el uso de los controladores:
	public ArrayList<Media> listForController(String listName, MediaLists mediaLists){
		ArrayList<Media> mediaArray = new ArrayList<Media>();
		switch(listName) {
		case "comingSoonMovies":
			mediaArray = mediaApiService.generateMediaByList(mediaLists.getComingSoonMovies());
			break;
		case "comingSoonSeries":
			mediaArray = mediaApiService.generateMediaByList(mediaLists.getComingSoonSeries());
			break;
		case "popularMovies":
			mediaArray = mediaApiService.generateMediaByList(mediaLists.getPopularMovies());
			break;
		case "popularSeries":
			mediaArray = mediaApiService.generateMediaByList(mediaLists.getPopularSeries());
			break;
		case "bestMovies":
			mediaArray = mediaApiService.generateMediaByList(mediaLists.getBestMovies());
			break;
		case "bestSeries":
			mediaArray = mediaApiService.generateMediaByList(mediaLists.getBestSeries());
			break;
		}
		
		return mediaArray;
	}
	
	
	
	
	
}
