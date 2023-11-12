package com.videosharing.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.videosharing.models.VideoRating;

@Repository
public interface VideoRatingRepository extends JpaRepository<VideoRating, Integer> {

	List<VideoRating> findByVideoId(Integer videoId);

	Optional<VideoRating> findByUserIdAndVideoId(Integer userId, Integer videoId);

}
