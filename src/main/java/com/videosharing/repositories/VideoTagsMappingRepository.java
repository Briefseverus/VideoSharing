package com.videosharing.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.videosharing.models.VideoTagsMapping;

@Repository
public interface VideoTagsMappingRepository extends JpaRepository<VideoTagsMapping, Integer> {

	Optional<VideoTagsMapping> findByTagId(Integer tagId);

	List<VideoTagsMapping> findAllByVideoId(Integer videoId);
}
