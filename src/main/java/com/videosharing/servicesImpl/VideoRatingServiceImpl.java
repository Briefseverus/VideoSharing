package com.videosharing.servicesImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.videosharing.models.VideoRating;
import com.videosharing.repositories.VideoRatingRepository;
import com.videosharing.services.VideoRatingService;

@Service
public class VideoRatingServiceImpl implements VideoRatingService {
	@Autowired
	private VideoRatingRepository videoRatingRepository;

	@Override
	public VideoRating getVideoRatingById(Integer id) {
		return videoRatingRepository.findById(id).orElse(null);
	}

	@Override
	public List<VideoRating> getAllVideoRatings() {
		return videoRatingRepository.findAll();
	}

	@Override
	public VideoRating createVideoRating(VideoRating videoRating) {
		return videoRatingRepository.save(videoRating);
	}

	@Override
	public VideoRating updateVideoRating(Integer id, VideoRating videoRating) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteVideoRating(Integer id) {
		// TODO Auto-generated method stub
		
	}

	// Cài đặt các phương thức tương tự cho VideoRating
}
