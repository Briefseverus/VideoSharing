package com.videosharing.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.videosharing.configs.CustomUserDetails;
import com.videosharing.dtos.VideoRatingDTO;
import com.videosharing.mappers.VideoRatingMapper;
import com.videosharing.models.VideoRating;
import com.videosharing.services.VideoRatingService;

@RestController
@RequestMapping("/api/video-ratings")
public class VideoRatingController {

	@Autowired
	private VideoRatingMapper videoRatingMapper;

	@Autowired
	private VideoRatingService videoRatingService;

	@GetMapping("/{videoId}")
	public int getVideoRatingByVideoId(@PathVariable Integer videoId) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		CustomUserDetails currentUserDetails = (CustomUserDetails) authentication.getPrincipal();
		Integer userId = currentUserDetails.getUser().getId();
		return videoRatingService.getRatingByUserIdAndVideoId(userId, videoId);
	}

	@GetMapping
	public List<VideoRatingDTO> getAllVideoRatings() {
		return videoRatingMapper.toDTOList(videoRatingService.getAllVideoRatings());
	}

	@PostMapping
	public VideoRatingDTO createVideoRating(@RequestBody VideoRatingDTO videoRatingDTO) throws Exception {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		CustomUserDetails currentUserDetails = (CustomUserDetails) authentication.getPrincipal();
		Integer userId = currentUserDetails.getUser().getId();
		videoRatingDTO.setUserId(userId);

		VideoRating videoRating = videoRatingMapper.toModel(videoRatingDTO);
		return videoRatingMapper.toDTO(videoRatingService.createVideoRating(videoRating));
	}

	@PutMapping("/{id}")
	public VideoRatingDTO updateVideoRating(@PathVariable Integer id, @RequestBody VideoRatingDTO videoRatingDTO) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		CustomUserDetails currentUserDetails = (CustomUserDetails) authentication.getPrincipal();

		Integer userId = currentUserDetails.getUser().getId();

		if (!videoRatingService.isOwner(userId, id)) {
			throw new AccessDeniedException("You are not the owner of this rating");
		}

		VideoRating videoRating = videoRatingMapper.toModel(videoRatingDTO);
		return videoRatingMapper.toDTO(videoRatingService.updateVideoRating(id, videoRating));
	}

	@DeleteMapping("/{id}")
	public void deleteVideoRating(@PathVariable Integer id) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		CustomUserDetails currentUserDetails = (CustomUserDetails) authentication.getPrincipal();

		Integer userId = currentUserDetails.getUser().getId();

		if (!videoRatingService.isOwner(userId, id)) {
			throw new AccessDeniedException("You are not the owner of this rating");
		}

		videoRatingService.deleteVideoRating(id);
	}

}
