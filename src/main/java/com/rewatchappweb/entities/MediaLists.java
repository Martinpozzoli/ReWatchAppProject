package com.rewatchappweb.entities;

import java.util.ArrayList;


public class MediaLists {

	private Integer id;
	private ArrayList<String> comingSoonMovies;
	private ArrayList<String> comingSoonSeries;
	private ArrayList<String> popularMovies;
	private ArrayList<String> popularSeries;
	private ArrayList<String> bestMovies;
	private ArrayList<String> bestSeries;
	
	public MediaLists(ArrayList<String> comingSoonMovies, ArrayList<String> comingSoonSeries, ArrayList<String> popularMovies,
			ArrayList<String> popularSeries, ArrayList<String> bestMovies, ArrayList<String> bestSeries) {
		this.comingSoonMovies = comingSoonMovies;
		this.comingSoonSeries = comingSoonSeries;
		this.popularMovies = popularMovies;
		this.popularSeries = popularSeries;
		this.bestMovies = bestMovies;
		this.bestSeries = bestSeries;
	}

	public MediaLists() {
		
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public ArrayList<String> getComingSoonMovies() {
		return comingSoonMovies;
	}

	public void setComingSoonMovies(ArrayList<String> comingSoonMovies) {
		this.comingSoonMovies = comingSoonMovies;
	}

	public ArrayList<String> getComingSoonSeries() {
		return comingSoonSeries;
	}

	public void setComingSoonSeries(ArrayList<String> comingSoonSeries) {
		this.comingSoonSeries = comingSoonSeries;
	}

	public ArrayList<String> getPopularMovies() {
		return popularMovies;
	}

	public void setPopularMovies(ArrayList<String> popularMovies) {
		this.popularMovies = popularMovies;
	}

	public ArrayList<String> getPopularSeries() {
		return popularSeries;
	}

	public void setPopularSeries(ArrayList<String> popularSeries) {
		this.popularSeries = popularSeries;
	}

	public ArrayList<String> getBestMovies() {
		return bestMovies;
	}

	public void setBestMovies(ArrayList<String> bestMovies) {
		this.bestMovies = bestMovies;
	}

	public ArrayList<String> getBestSeries() {
		return bestSeries;
	}

	public void setBestSeries(ArrayList<String> bestSeries) {
		this.bestSeries = bestSeries;
	}
	
	
}
