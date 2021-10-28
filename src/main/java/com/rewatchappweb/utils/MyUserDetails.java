package com.rewatchappweb.utils;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.rewatchappweb.entidades.Usuario;

public class MyUserDetails implements UserDetails{

	/**
	 * No se para que me lo pide
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	private String userName;
	private String password;
	private boolean active;
	private List<GrantedAuthority> authorities;
	
	
	public MyUserDetails(Usuario usuario) {
		this.userName = usuario.getNombre();
		this.password = usuario.getPassword();
		this.active = usuario.isActive();
		this.authorities = Arrays.stream(usuario.getRoles().split(","))
				.map(SimpleGrantedAuthority::new)
				.collect(Collectors.toList());
	}


	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}


	@Override
	public String getPassword() {
		return password;
	}


	@Override
	public String getUsername() {
		return userName;
	}


	@Override
	public boolean isAccountNonExpired() {
		return true;
	}


	@Override
	public boolean isAccountNonLocked() {
		return true;
	}


	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}


	@Override
	public boolean isEnabled() {
		return active;
	}


}
