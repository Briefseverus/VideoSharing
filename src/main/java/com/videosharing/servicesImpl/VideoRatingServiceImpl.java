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
	public VideoRating updateVideoRating(Integer userId,Integer id, VideoRating videoRating) {
//		VideoRating videoRating = videoRatingRepository.findByUserIdAndVideoId(userId, videoRating.getVideo().getId()).orElse(null);
//		    if (existingVideoRating != null) {
//		        existingVideoRating.setRating(videoRating.getRating());
//		        return videoRatingRepository.save(existingVideoRating);
//		    } else {
//		        return null;
//		    }
		return null;
	}

	@Override
	public void deleteVideoRating(Integer id) {
	    VideoRating videoRating = videoRatingRepository.findById(id).orElse(null);
	    if (videoRating != null) {
	        videoRatingRepository.delete(videoRating);
	    }
	}
	
	
	@Override
	public void deleteVideoRating(Integer userId, Integer videoId) {
		VideoRating videoRating = videoRatingRepository.findByUserIdAndVideoId(userId, videoId).orElse(null);
		if (videoRating != null) {
			 videoRatingRepository.delete(videoRating);
		} 

	}
	@Override
	public VideoRating updateVideoRating(Integer id, VideoRating videoRating) {
	    VideoRating existingVideoRating = videoRatingRepository.findById(id).orElse(null);
	    if (existingVideoRating != null) {
	        existingVideoRating.setRating(videoRating.getRating());
	        return videoRatingRepository.save(existingVideoRating);
	    } else {
	        return null;
	    }
	}
	
	@Override
	public Integer getAverageRatingByVideoId(Integer videoId) {
		List<VideoRating> videoRatings = videoRatingRepository.findByVideoId(videoId);
		if (!videoRatings.isEmpty()) {
			int totalRating = 0;
			for (VideoRating videoRating : videoRatings) {
				totalRating += videoRating.getRating();
			}
			return totalRating / videoRatings.size();
		} else {
			return null;
		}

	}

	@Override
	public Integer getRatingByUserIdAndVideoId(Integer userId, Integer videoId) {
		VideoRating videoRating = videoRatingRepository.findByUserIdAndVideoId(userId, videoId).orElse(null);
		if (videoRating != null) {
			return videoRating.getRating();
		} else {
			return null;
		}
	}

}
