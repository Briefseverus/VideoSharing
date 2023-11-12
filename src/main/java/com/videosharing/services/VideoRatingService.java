package com.videosharing.services;

import java.util.List;

import com.videosharing.models.VideoRating;

public interface VideoRatingService {
	VideoRating getVideoRatingById(Integer id);

	List<VideoRating> getAllVideoRatings();

	VideoRating createVideoRating(VideoRating videoRating);

	VideoRating updateVideoRating(Integer id, VideoRating videoRating);
	
	VideoRating updateVideoRating(Integer userId,Integer id, VideoRating videoRating);

	void deleteVideoRating(Integer id);

	Integer getAverageRatingByVideoId(Integer videoId);
	
	void deleteVideoRating(Integer userId, Integer videoId);

	Integer getRatingByUserIdAndVideoId(Integer userId, Integer videoId);
}
