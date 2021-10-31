package com.rewatchappweb.utils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

@Service
public class PeliculasAPI {
	
	//Clave de cuenta de imdb:
	final String myKey = "k_a0jbzhwo";
	
	//Mejores peliculas para la biblioteca del Home, despues cambiar por estrenos
	public JSONObject buscarTop() {
		
		HttpURLConnection connection = null;
		

		try {
			URL url = new URL("https://imdb-api.com/en/API/Top250Movies/" + myKey);
			connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			connection.setDoInput(true);

			InputStream stream = connection.getInputStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
			StringBuilder responce = new StringBuilder();
			String line = null;
			while ((line = reader.readLine()) != null) {
				responce.append(line);
				responce.append("\r");
			}
			reader.close();
			String result = responce.toString();
			System.out.print(result);

			JSONObject object = new JSONObject(result);
			System.out.println(object.toString());
			return object;
//			String id = object.getString("imDbId");
//			String title = object.getString("title");
//			String realeseYear = object.getString("year");
//			String ratings = object.getString("imDbRating");
//
//			System.out.println("Movie id " + id);
//			System.out.println("Movie title " + title);
//			System.out.println("Movie year " + realeseYear);
//			System.out.println("Movie Ratings " + ratings);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	//Ejemplo del asiático de Youtube
	public JSONObject buscarPorID() {
		
		HttpURLConnection connection = null;
		

		try {
			URL url = new URL("https://imdb-api.com/en/API/Ratings/" + myKey + "/tt1375666");
			connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			connection.setDoInput(true);

			InputStream stream = connection.getInputStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
			StringBuilder responce = new StringBuilder();
			String line = null;
			while ((line = reader.readLine()) != null) {
				responce.append(line);
				responce.append("\r");
			}
			reader.close();
			String result = responce.toString();
			System.out.print(result);

			JSONObject object = new JSONObject(result);

			String id = object.getString("imDbId");
			String title = object.getString("title");
			String realeseYear = object.getString("year");
			String ratings = object.getString("imDb");

			System.out.println("Movie id " + id);
			System.out.println("Movie title " + title);
			System.out.println("Movie year " + realeseYear);
			System.out.println("Movie Ratings " + ratings);
			
			return object;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
