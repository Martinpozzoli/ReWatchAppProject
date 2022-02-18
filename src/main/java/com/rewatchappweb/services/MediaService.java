package com.rewatchappweb.services;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rewatchappweb.entities.Media;
import com.rewatchappweb.entities.MediaLists;
import com.rewatchappweb.repositories.MediaListsRepository;
import com.rewatchappweb.repositories.MediaRepository;
import com.rewatchappweb.utils.MediaAPI;

@Transactional
@Service
public class MediaService {
	
	@Autowired
	private MediaRepository mediaRepo;
	
	@Autowired
	private MediaAPIService mediaApiService;
	
	
	//Busca en la base de datos y si no se encuentra, se busca a traves de la api de imdb ------------------------------------------------------------
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
	
	//Agregar media a la base de datos para evitar el exceso de consultas a las APIs que son limitadas: --------------------------------------------------
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
	//Falta una solución para evitar el exceso de peticiones, hallar una manera de guardar las listas --------------------------------------------------
	@Autowired
	private MediaAPI mediaApi;
	
	@Autowired
	private MediaListsRepository mediaListsRepo;
	
	public MediaLists updateMediaLists() {
		ArrayList<String> comingSoonMovies = new ArrayList<String>();
//		ArrayList<String> comingSoonSeries = new ArrayList<String>();
		ArrayList<String> popularMovies = new ArrayList<String>();
//		ArrayList<String> popularSeries = new ArrayList<String>();
		ArrayList<String> bestMovies = new ArrayList<String>();
//		ArrayList<String> bestSeries = new ArrayList<String>();
		
		//Quiero que la aplicacion pueda actualizar las listas cada 7 días:---------------
		
		MediaLists mediaLists = mediaListsRepo.getById(1);
		try {
			Period sinceLastRefresh = Period.between(mediaLists.getLastRefreshDate(), LocalDate.now());
			if(sinceLastRefresh.getDays() >= 7) {
				comingSoonMovies = mediaApi.selectedAPIList("comingSoonMovies");
//				comingSoonSeries = mediaApi.selectedAPIList("comingSoonTV");
				popularMovies = mediaApi.selectedAPIList("popularMovies");
//				popularSeries = mediaApi.selectedAPIList("popularTV");
				bestMovies = mediaApi.selectedAPIList("topRatedMovies");
//				bestSeries = mediaApi.selectedAPIList("topRatedTV");
			}else {
				comingSoonMovies = mediaLists.getComingSoonMovies();
//				comingSoonSeries = mediaLists.getComingSoonSeries();
				popularMovies = mediaLists.getPopularMovies();
//				popularSeries = mediaLists.getPopularSeries();
				bestMovies = mediaLists.getBestMovies();
//				bestSeries = mediaLists.getBestSeries();
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		mediaLists.setComingSoonMovies(comingSoonMovies);
//		mediaLists.setComingSoonSeries(comingSoonSeries);
		mediaLists.setPopularMovies(popularMovies);
//		mediaLists.setPopularSeries(popularSeries);
		mediaLists.setBestMovies(bestMovies);
//		mediaLists.setBestSeries(bestSeries);
		
		mediaListsRepo.save(mediaLists);
		
		cleanUselessMedia();
		
		return(mediaLists);
	}
	
	//Devolver las listas anteriores para el uso de los controladores: ---------------------------------------------------------------------
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
	
	//Obtiene los ids de medias que no tienen titulo y borra los 
	//registros de la base de datos y de las listas que las contenian: -------------------------------------------------------------
	public void cleanUselessMedia() {
		List<String> idsNullMedia = mediaRepo.getIdsOfNullTitlesFromMedia();
		MediaLists mLists = mediaListsRepo.getById(1);
		
		mLists.getBestMovies().removeAll(idsNullMedia);
		mLists.getBestSeries().removeAll(idsNullMedia);
		mLists.getPopularMovies().removeAll(idsNullMedia);
		mLists.getPopularSeries().removeAll(idsNullMedia);
		mLists.getComingSoonMovies().removeAll(idsNullMedia);
		mLists.getComingSoonSeries().removeAll(idsNullMedia);
		
		mediaListsRepo.save(mLists);
		
		mediaRepo.deleteNullTitlesFromMedia();
		
	}
	
	//Busqueda de peliculas por titulo, cast y director--------------------------------------------------------------------------------
	public List<Media> searchByComparison(String p){
		List<Media> mediaList = new ArrayList<Media>();
		
		try {
			mediaList.addAll(mediaRepo.getListByTitle(p));
			
			mediaList.addAll(mediaRepo.getListByCast(p));
			mediaList.addAll(mediaRepo.getListByDirector(p));
		}catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(mediaRepo.getListByTitle(p).toString());
		return mediaList;
	}
	
	
	
	
	
	
}
