package com.videosharing.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.videosharing.models.Video;

@Repository
public interface VideoRepository extends JpaRepository<Video, Integer> {
	
}
