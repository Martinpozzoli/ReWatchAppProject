package com.rewatchappweb.entities;

import java.time.LocalDate;
import java.util.ArrayList;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="mediaLists")
public class MediaLists {

	@Id
	private Integer id;
	@Column
	private ArrayList<String> comingSoonMovies;
	@Column
	private ArrayList<String> comingSoonSeries;
	@Column
	private ArrayList<String> popularMovies;
	@Column
	private ArrayList<String> popularSeries;
	@Column
	private ArrayList<String> bestMovies;
	@Column
	private ArrayList<String> bestSeries;
	@Column
	private LocalDate lastRefreshDate;

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

	public LocalDate getLastRefreshDate() {
		return lastRefreshDate;
	}

	public void setLastRefreshDate(LocalDate lastRefreshDate) {
		this.lastRefreshDate = lastRefreshDate;
	}
	
	
}
