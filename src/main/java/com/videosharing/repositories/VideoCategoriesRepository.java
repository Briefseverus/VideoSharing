package com.videosharing.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.videosharing.models.VideoCategories;

@Repository
public interface VideoCategoriesRepository extends JpaRepository<VideoCategories, Integer> {
	Optional<VideoCategories> findByCategoriesId(Integer categoriesId);

	List<VideoCategories> findAllByVideoId(Integer videoId);
}
