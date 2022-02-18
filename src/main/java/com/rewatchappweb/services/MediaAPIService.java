package com.rewatchappweb.services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rewatchappweb.entities.Media;
import com.rewatchappweb.errors.ErrorServicio;
import com.rewatchappweb.utils.MediaAPI;

@Service
public class MediaAPIService {

	@Autowired
	private MediaAPI mediaAPI;
	
	@Autowired
	private MediaService mediaService;

	//Estos método se encarga de pedir un listado de IDs a selectedAPIList segun lo indique el usuario,
	//pasa los datos uno por uno a generateMedia para armar un array de media con los datos requeridos
	public ArrayList<Media> generateUserMediaByList(List<String> mediaIDs) {
		
		ArrayList<Media> mediaList = new ArrayList<Media>();
		Media m = new Media();
		for (int i = 0; i < mediaIDs.size(); i++) {
			m = mediaService.findMedia(mediaIDs.get(i));
			System.out.println(m.toString());
			mediaList.add(m);
		}
		return mediaList;
	}
	
	public ArrayList<Media> generateMediaByList(List<String> mediaIDs) {
		
		ArrayList<Media> mediaList = new ArrayList<Media>();
		Media m = new Media();
		for (int i = 0; i < 50; i++) {
			m = mediaService.findMedia(mediaIDs.get(i));
			System.out.println(m.toString());
			mediaList.add(m);
		}
		return mediaList;
	}
	
	//Recibe el ID de la pelicula o serie (Ej: "tt0411008") y genera el objeto Media ---------------------------------------------
	public Media generateMedia(String id) throws ErrorServicio{

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

	//Genera el arreglo para guardarlo como atributo en Media (Revisar error al traer la primer letra, probar con la api)
	//Creo que ya lo corregí, falta actualizar todo y ver que pasa.(borrar todas las medias anteriores cuando se reseteen las peticiones)
	
	public String validarArray(String str) {
		String finalString = "";
		String cont = "";
		str = str + "!";
		boolean flag = false;
		int i = 0;
		while (i < str.length()) {
			if (str.charAt(i) != ',' && str.charAt(i) != '!') {
				flag = true;
			}
			while (flag == true) {
				if (str.charAt(i) != ',' && str.charAt(i) != '!') {
					cont = cont + str.charAt(i);
				}
				if (str.charAt(i) == ',' || str.charAt(i) == '!') {
					finalString = finalString + cont;
					cont = " ";
					flag = false;
				}
				i++;
			}
		}
		return finalString;
	}
}
