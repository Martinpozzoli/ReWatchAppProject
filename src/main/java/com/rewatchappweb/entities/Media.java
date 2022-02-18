package com.rewatchappweb.entities;

import java.time.LocalDate;

import javax.persistence.*;

@Entity
@Table(name="media")
public class Media {
	@Id
	private String id;
	private String title;
	private int year;
	private String image;
	private LocalDate releaseDate;
	private String plot;
	private int runtime;
	private String genres;
	private String countries;
	private String stars;
	private String directors;
	private String imDbRating;
	private String contentRating;
	
	public Media() {
	
	}

	public Media(String id, String title, int year, String image, LocalDate releaseDate, String plot,
			int runtime, String genres, String countries, String stars, 
			String directors, String imDbRating, String contentRating) {
		super();
		this.id = id;
		this.title = title;
		this.year = year;
		this.image = image;
		this.releaseDate = releaseDate;
		this.plot = plot;
		this.runtime = runtime;
		this.genres = genres;
		this.countries = countries;
		this.stars = stars;
		this.directors = directors;
		this.imDbRating = imDbRating;
		this.contentRating = contentRating;
	}


	public Media(String id) {
		super();
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public LocalDate getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(LocalDate releaseDate) {
		this.releaseDate = releaseDate;
	}

	public String getPlot() {
		return plot;
	}

	public void setPlot(String plot) {
		this.plot = plot;
	}

	public String getGenres() {
		return genres;
	}

	public void setGenres(String genres) {
		this.genres = genres;
	}

	public String getCountries() {
		return countries;
	}

	public void setCountries(String countries) {
		this.countries = countries;
	}

	public String getStars() {
		return stars;
	}

	public void setStars(String stars) {
		this.stars = stars;
	}

	public String getDirectors() {
		return directors;
	}

	public void setDirectors(String directors) {
		this.directors = directors;
	}

	public String getImDbRating() {
		return imDbRating;
	}

	public void setImDbRating(String imDbRating) {
		this.imDbRating = imDbRating;
	}

	public String getContentRating() {
		return contentRating;
	}

	public void setContentRating(String contentRating) {
		this.contentRating = contentRating;
	}

	public int getRuntime() {
		return runtime;
	}

	public void setRuntime(int runtime) {
		this.runtime = runtime;
	}

	@Override
	public String toString() {
		return "Media [id=" + id + ", title=" + title + ", year=" + year + ", image=" + image + ", releaseDate="
				+ releaseDate + ", plot=" + plot + ", genres=" + genres + ", countries=" + countries + ", stars="
				+ stars + ", directors=" + directors + ", imDbRating=" + imDbRating + ", contentRating=" + contentRating
				+ "]";
	}

	
}
