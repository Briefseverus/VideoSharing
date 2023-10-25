package com.videosharing.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.videosharing.models.VideoCategories;

@Repository
public interface VideoCategoriesRepository extends JpaRepository<VideoCategories, Integer> {

}
