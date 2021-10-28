package com.rewatchappweb.entidades;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;

@Entity
@Table(name="usuario")
public class Usuario {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	private String nombre;
	private Integer edad;
	private String email;
	private String password;
	private boolean active;
	private String roles;
	
	@ManyToMany(
				cascade = CascadeType.MERGE,
				fetch = FetchType.EAGER
				)
	@JoinTable(
			name = "user_media",
			joinColumns = {@JoinColumn(name="user_id")},
			inverseJoinColumns = {@JoinColumn(name = "media_id")})
	private Set<Media> mediaList = new HashSet<Media>();
	
	public Usuario() {
		super();
	}

	public Usuario(String nombre, Integer edad, String email, String password, boolean active, String roles) {
		this.nombre = nombre;
		this.edad = edad;
		this.email = email;
		this.password = password;
		this.active = active;
		this.roles = roles;
	}

	public Usuario(String nombre, Integer edad, String email, String password) {
		super();
		this.nombre = nombre;
		this.edad = edad;
		this.email = email;
		this.password = password;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public String getRoles() {
		return roles;
	}

	public void setRoles(String roles) {
		this.roles = roles;
	}

	public Set<Media> getMediaList() {
		return mediaList;
	}

	public void setMediaList(Set<Media> mediaList) {
		this.mediaList = mediaList;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Integer getEdad() {
		return edad;
	}

	public void setEdad(Integer edad) {
		this.edad = edad;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public void agregarMedia(Media m) {
		mediaList.add(m);
		m.agregarUsuario(this);
	}
	
	public void eliminarMedia(Media m) {
		mediaList.remove(m);
		m.eliminarUsuario(this);
	}
}
