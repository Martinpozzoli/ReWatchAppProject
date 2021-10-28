package com.rewatchappweb.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;

import com.rewatchappweb.entidades.Usuario;

@Repository
@Service
//@Transactional
public interface IUsuarioDAO extends CrudRepository<Usuario,Integer>{

//	@EntityManager
//	EntityManager entityManager;
//	
//	public List<Usuario> getUsuario();
}
