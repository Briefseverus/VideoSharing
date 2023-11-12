package com.videosharing.mappers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.videosharing.dtos.VideoRatingDTO;
import com.videosharing.models.VideoRating;
import com.videosharing.services.UserService;
import com.videosharing.services.VideoService;

@Component
public class VideoRatingMapper {
	@Autowired
	private VideoService videoService;
	
	@Autowired
	private UserService userService;

	public  VideoRatingDTO toDTO(VideoRating model) {
		VideoRatingDTO dto = new VideoRatingDTO();
		dto.setId(model.getId());
		dto.setVideoId(model.getVideo().getId());
		dto.setUserId(model.getUser().getId());
		dto.setRating(model.getRating());
		dto.setRatedDate(model.getRatedDate());
		return dto;
	}

	public  List<VideoRatingDTO> toDTOList(List<VideoRating> models) {
		return models.stream().map(cat -> toDTO(cat)).collect(Collectors.toList());
	}

	public  VideoRating toModel(VideoRatingDTO dto) {
		VideoRating model = new VideoRating();
		model.setId(dto.getId());
		model.setVideo(videoService.getVideoById(dto.getVideoId()));
		model.setUser(userService.getUserById(dto.getUserId()));
		model.setRating(dto.getRating());
		model.setRatedDate(dto.getRatedDate());
		return model;
	}

}