package com.rewatchappweb.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.rewatchappweb.entities.Media;

@Repository
public interface MediaRepository extends JpaRepository<Media,String>{

	@Query("SELECT m FROM Media m WHERE m.title =:title")
	public Media findByTitle(@Param("title")String title);
}
