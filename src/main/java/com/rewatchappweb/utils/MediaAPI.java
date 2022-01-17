package com.rewatchappweb.utils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

@Service
public class MediaAPI {

	// Clave de cuenta de imdb:
	final String myKey = "";
	
	//Listas que devuelven los ID de la media:
	public ArrayList<String> selectedAPIList(String listName) {
		String listUrl="";
		switch(listName) {
		case "popularMovies":	
			listUrl = "https://imdb8.p.rapidapi.com/title/get-most-popular-movies?homeCountry=US&purchaseCountry=US&currentCountry=US";
			break;
		case "topRatedMovies":
			listUrl = "https://imdb8.p.rapidapi.com/title/get-top-rated-movies";
			break;
		case "comingSoonMovies":
			listUrl = "https://imdb8.p.rapidapi.com/title/get-coming-soon-movies?homeCountry=US&purchaseCountry=US&currentCountry=US";
			break;
		case "popularTV":
			listUrl = "https://imdb8.p.rapidapi.com/title/get-most-popular-tv-shows?homeCountry=US&purchaseCountry=US&currentCountry=US";
			break;
		case "topRatedTV":
			listUrl = "https://imdb8.p.rapidapi.com/title/get-top-rated-tv-shows";
			break;
		case "comingSoonTV":
			listUrl = "https://imdb8.p.rapidapi.com/title/get-coming-soon-tv-shows?currentCountry=US";
			break;
		}
		ArrayList<String> mediaIDList = findMediaIDs(listUrl);
		return mediaIDList;
	}
	
	//Este método solo obtiene los ID de las peliculas con la API de rapidapi: imdb8
	//Una vez obtenidos se puede llamar a findById y asi obtengo los atributos y datos de cada media.
	public ArrayList<String> findMediaIDs(String listUrl) {

		try {

			HttpRequest request = HttpRequest.newBuilder().uri(URI.create(listUrl))
					//this space is where API keys are, they are private, sorry :)
					.method("GET", HttpRequest.BodyPublishers.noBody()).build();
			HttpResponse<String> response = HttpClient.newHttpClient().send(request,
					HttpResponse.BodyHandlers.ofString());

			//System.out.println(response.body());
			
			ArrayList<String> ids = new ArrayList<String>();
			String title = "";
			boolean flag = false;
			for (int i = 0; i < response.body().length(); i++) {
				if (response.body().charAt(i) == '/' && response.body().charAt(i + 1) == 't'
						&& response.body().charAt(i + 2) == 't') {
					flag = true;
				}
				while(flag == true) {
					i++;
					if (response.body().charAt(i) != '/') {
						title = title + response.body().charAt(i);
					}
					if (response.body().charAt(i + 1) == '/') {
						ids.add(title);
						title = "";
						flag = false;
					}
				}
				
			}
			
			System.out.println(ids.toString());
			
			return ids;
		} catch (

		Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	//Recibe el ID de la media, busca los atributos en la api de imdb y devuelve un objeto JSON--------------------------------
	public JSONObject findByID(String id) {

		HttpURLConnection connection = null;

		try {
			URL url = new URL("https://imdb-api.com/en/API/Title/" + myKey + "/" + id);
			connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			connection.setDoInput(true);

			InputStream stream = connection.getInputStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
			StringBuilder response = new StringBuilder();
			String line = null;
			while ((line = reader.readLine()) != null) {
				response.append(line);
				response.append("\r");
			}
			reader.close();
			String result = response.toString();

			JSONObject object = new JSONObject(result);
			
			return object;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
