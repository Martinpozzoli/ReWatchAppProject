package com.rewatchappweb.utils;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.rewatchappweb.dao.IUserRepository;
import com.rewatchappweb.entidades.Usuario;

@Service
public class MyUserDetailsService implements UserDetailsService{
	
	@Autowired
	IUserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		Optional<Usuario> user = userRepository.findByNombre(userName);
		
		user.orElseThrow(()-> new UsernameNotFoundException("Not Found: " + userName));
		return user.map(MyUserDetails::new).get();
	}

	
}
