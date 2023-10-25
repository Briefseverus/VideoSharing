package com.videosharing.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.videosharing.models.VideoView;

@Repository
public interface VideoViewRepository extends JpaRepository<VideoView, Integer> {
	
}
