package com.videosharing.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.videosharing.models.VideoRating;

@Repository
public interface VideoRatingRepository extends JpaRepository<VideoRating, Integer> {

}
