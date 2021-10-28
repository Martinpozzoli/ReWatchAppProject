package com.rewatchappweb.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.rewatchappweb.entidades.Usuario;

@Repository

public interface UsuarioRepositorio extends JpaRepository<Usuario,Integer>{
	
	@Query("SELECT u FROM Usuario u WHERE u.email =:email")
	public Usuario findByEmail(@Param("email")String email);
}
