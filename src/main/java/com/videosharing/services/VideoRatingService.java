package com.videosharing.services;

import java.util.List;

import com.videosharing.models.VideoRating;

public interface VideoRatingService {
    VideoRating getVideoRatingById(Integer id);
    List<VideoRating> getAllVideoRatings();
    VideoRating createVideoRating(VideoRating videoRating);
    // Các phương thức tương tự cho VideoRating
	VideoRating updateVideoRating(Integer id, VideoRating videoRating);
	void deleteVideoRating(Integer id);
}
