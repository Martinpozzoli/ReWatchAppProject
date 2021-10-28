package com.rewatchappweb.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.rewatchappweb.entidades.Usuario;

@Repository

public interface IUserRepository extends JpaRepository<Usuario,Integer>{
	
	Optional<Usuario> findByNombre(String nombre);
	
	@Query("SELECT u FROM Usuario u WHERE u.email = email")
	public Usuario findByEmail(@Param("email")String email);
}
