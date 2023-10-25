package com.videosharing.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.videosharing.models.VideoTagsMapping;

@Repository
public interface VideoTagsMappingRepository extends JpaRepository<VideoTagsMapping, Integer> {
	
}
