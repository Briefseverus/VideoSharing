package com.videosharing.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.videosharing.dtos.VideoRatingDTO;
import com.videosharing.mappers.VideoRatingMapper;
import com.videosharing.services.VideoRatingService;

@RestController
@RequestMapping("/api/video-ratings")
public class VideoRatingController {

	@Autowired
	private VideoRatingService videoRatingService;

	@GetMapping("/{id}")
	public VideoRatingDTO getVideoRatingById(@PathVariable Integer id) {
		return VideoRatingMapper.toDTO(videoRatingService.getVideoRatingById(id));
	}

	@GetMapping
	public List<VideoRatingDTO> getAllVideoRatings() {
		return VideoRatingMapper.toDTOList(videoRatingService.getAllVideoRatings());
	}

	@PostMapping
	public VideoRatingDTO createVideoRating(@RequestBody VideoRatingDTO videoRatingDTO) {
		return VideoRatingMapper.toDTO(videoRatingService.createVideoRating(VideoRatingMapper.toModel(videoRatingDTO)));
	}

	@PutMapping("/{id}")
	public VideoRatingDTO updateVideoRating(@PathVariable Integer id, @RequestBody VideoRatingDTO videoRatingDTO) {
		return VideoRatingMapper
				.toDTO(videoRatingService.updateVideoRating(id, VideoRatingMapper.toModel(videoRatingDTO)));
	}

	@DeleteMapping("/{id}")
	public void deleteVideoRating(@PathVariable Integer id) {
		videoRatingService.deleteVideoRating(id);
	}

}