package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.AnnouncementCategory;

@Repository
public interface AnnouncementCategoryRepository extends JpaRepository<AnnouncementCategory, String> {

}
