package com.rewatchappweb.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.rewatchappweb.entities.Media;

@Repository
public interface MediaRepository extends JpaRepository<Media,String>{

	@Query("SELECT m FROM Media m WHERE m.title =:title")
	public Media findByTitle(@Param("title")String title);
	
	@Query("SELECT m FROM Media m WHERE m.title LIKE CONCAT('%',:title,'%')")
	public List<Media> getListByTitle(@Param("title")String title);
	
	@Query("SELECT m FROM Media m WHERE m.stars LIKE CONCAT('%',:name,'%')")
	public List<Media> getListByCast(@Param("name") String name);
	
	@Query("SELECT m FROM Media m WHERE m.directors LIKE CONCAT('%',:name,'%')")
	public List<Media> getListByDirector(@Param("name") String name);
	
	@Query("SELECT m.id FROM Media m")
	public List<String> getAllIdsFromMedia();
	
	@Query("SELECT m.id FROM Media m WHERE m.title =''")
	public List<String> getIdsOfNullTitlesFromMedia();
	
	@Modifying
	@Query("DELETE FROM Media m WHERE m.title =''")
	public void deleteNullTitlesFromMedia();
}
