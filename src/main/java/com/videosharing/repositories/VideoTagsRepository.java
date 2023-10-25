package com.videosharing.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.videosharing.models.VideoTags;

@Repository
public interface VideoTagsRepository extends JpaRepository<VideoTags, Integer> {
	
}
