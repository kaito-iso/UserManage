package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Announcements;

@Repository
public interface AnnouncementsRepository extends JpaRepository<Announcements, Integer> {

	@Query("SELECT a FROM Announcements a LEFT JOIN FETCH a.category ORDER BY a.addDate DESC")
	List<Announcements> findAllWithCategory();

	/**
	 * 
	 */
	@Query("SELECT a FROM Announcements a " +
			"LEFT JOIN FETCH a.category " +
			"WHERE a.publishDate <= CURRENT_TIMESTAMP " +
			"AND (a.expiresDate IS NULL OR a.expiresDate >= CURRENT_TIMESTAMP) " +
			"ORDER BY a.addDate DESC")
	List<Announcements> publicFindAll();

}
