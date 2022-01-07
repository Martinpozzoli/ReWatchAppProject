package com.rewatchappweb.services;

import java.time.LocalDate;
import java.util.ArrayList;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rewatchappweb.entities.Media;
import com.rewatchappweb.utils.MediaAPI;

@Service
public class MediaAPIService {

	@Autowired
	private MediaAPI mediaAPI;
	
	@Autowired
	private MediaService mediaService;

	//Este método se encarga de pedir un listado de IDs a selectedAPIList segun lo indique el usuario,
	//pasa los datos uno por uno a generateMedia para armar un array de media con los datos requeridos
	public ArrayList<Media> generateMediaByList(ArrayList<String> mediaIDs) {
		
		ArrayList<Media> mediaList = new ArrayList<Media>();
		Media m = new Media();
		for (int i = 0; i < 20; i++) {
			m = mediaService.findMedia(mediaIDs.get(i));
			System.out.println(m.toString());
			mediaList.add(m);
		}
		return mediaList;
	}
	
	//Recibe el ID de la pelicula o serie (Ej: "tt0411008") y genera el objeto Media ---------------------------------------------
	public Media generateMedia(String id) {

		JSONObject object = mediaAPI.findByID(id);

		String title = object.optString("title");
		String releaseYear = object.optString("year");
		String image = object.optString("image");
		String releaseDate = object.optString("releaseDate");
		String runtime = object.optString("runtimeMins");
		String plot = object.optString("plot");
		String genres = object.optString("genres");
		String countries = object.optString("countries");
		String stars = object.optString("stars");
		String directors = object.optString("directors");
		String imDbRating = object.optString("imDbRating");
		String contentRating = object.optString("contentRating");

		Media m = new Media();

		m.setId(id);
		m.setTitle(title);
		m.setImage(image);
		//---------------------------------------------------
		if(releaseYear.isEmpty() || releaseYear.equals("") || releaseYear.isBlank()) {
			m.setYear(0);
		}else {
			m.setYear(Integer.parseInt(releaseYear));
		}
		if(releaseDate.isEmpty() || releaseDate.equals("") || releaseDate.isBlank()) {
			m.setReleaseDate(null);
		}else {
			m.setReleaseDate(LocalDate.parse(releaseDate));
		}
		if(runtime.isEmpty() || runtime.equals("") || runtime.isBlank()) {
			m.setRuntime(0);
		}else {
			m.setRuntime(Integer.parseInt(runtime));
		}
		//------------------------------------------------------
		m.setPlot(plot);
		m.setGenres(validarArray(genres));
		m.setCountries(validarArray(countries));
		m.setStars(validarArray(stars));
		m.setDirectors(validarArray(directors));
		m.setImDbRating(imDbRating);
		m.setContentRating(contentRating);

		return m;

	}
	
//	public String validateJSONResponse() {
//		if(runtime.isEmpty() || runtime.equals("") || runtime.isBlank()) {
//			m.setRuntime(0);
//		}else {
//			m.setRuntime(Integer.parseInt(runtime));
//		}
//	}

	//Genera el arreglo para guardarlo como atributo en Media
	public ArrayList<String> validarArray(String str) {
		ArrayList<String> arr = new ArrayList<String>();
		String cont = "";
		str = str + "!";
		boolean flag = false;
		for (int i = 0; i < str.length(); i++) {
			if (str.charAt(i) != ',' && str.charAt(i) != '!') {
				flag = true;
			}
			while (flag == true) {
				i++;
				if (str.charAt(i) != ',' && str.charAt(i) != '!') {
					cont = cont + str.charAt(i);
				}
				if (str.charAt(i) == ',' || str.charAt(i) == '!') {
					arr.add(cont);
					cont = "";
					flag = false;
				}
			}
		}
		return arr;
	}
}
