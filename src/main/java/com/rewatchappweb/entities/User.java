package com.rewatchappweb.entities;

import java.time.LocalDate;
import java.util.ArrayList;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;


@Entity
@Table(name="usuario")
public class User {
	@Id
	@GeneratedValue(generator="uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private String id;
	private String firstname;
	private String lastname;
	private LocalDate birthdate;
	private String email;
	private String password;
	private boolean active;
	@Enumerated(EnumType.STRING)
	private Role role;
	
	private ArrayList<String> favoritesList;
	private ArrayList<String> waitingList;
	private ArrayList<String> alreadySeenList;

	public User() {
		this.favoritesList = new ArrayList<String>();
		this.waitingList = new ArrayList<String>();
		this.alreadySeenList = new ArrayList<String>();
	}

	public User(String firstname, String lastname, LocalDate birthdate, String email, String password, boolean active) {
		this.firstname = firstname;
		this.lastname = lastname;
		this.birthdate = birthdate;
		this.email = email;
		this.password = password;
		this.active = active;
		this.favoritesList = new ArrayList<String>();
		this.waitingList = new ArrayList<String>();
		this.alreadySeenList = new ArrayList<String>();
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public LocalDate getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(LocalDate birthdate) {
		this.birthdate = birthdate;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public ArrayList<String> getFavoritesList() {
		return favoritesList;
	}

	public void setFavoritesList(ArrayList<String> favoritesList) {
		this.favoritesList = favoritesList;
	}

	public ArrayList<String> getWaitingList() {
		return waitingList;
	}

	public void setWaitingList(ArrayList<String> waitingList) {
		this.waitingList = waitingList;
	}

	public ArrayList<String> getAlreadySeenList() {
		return alreadySeenList;
	}

	public void setAlreadySeenList(ArrayList<String> alreadySeenList) {
		this.alreadySeenList = alreadySeenList;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", firstname=" + firstname + ", lastname=" + lastname + ", birthdate=" + birthdate
				+ ", email=" + email + ", password=" + password + ", active=" + active + ", role=" + role
				+ ", favoritesList=" + favoritesList + ", waitingList=" + waitingList + ", alreadySeenList="
				+ alreadySeenList + "]";
	}

}
