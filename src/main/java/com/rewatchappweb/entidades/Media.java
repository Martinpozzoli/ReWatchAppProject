package com.rewatchappweb.entidades;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name="media")
public class Media {
	@Id
	private Integer id;
	private String titulo;
	private int year;
	@ManyToMany(mappedBy = "mediaList")
	private Set<Usuario> userList = new HashSet<Usuario>();
	
	public Media() {
		super();
	}

	public Media(String titulo, int year) {
		super();
		this.titulo = titulo;
		this.year = year;
	}

	public Media(Integer id, String titulo, int year) {
		super();
		this.id = id;
		this.titulo = titulo;
		this.year = year;
	}

	public Media(Integer id) {
		super();
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public Set<Usuario> getUserList() {
		return userList;
	}

	public void setUserList(Set<Usuario> userList) {
		this.userList = userList;
	}
	
	public void agregarUsuario(Usuario u) {
		userList.add(u);
	}
	
	public void eliminarUsuario(Usuario u) {
		userList.remove(u);
	}
	
}
