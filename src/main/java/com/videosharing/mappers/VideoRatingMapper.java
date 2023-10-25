package com.videosharing.mappers;

import java.util.List;
import java.util.stream.Collectors;

import com.videosharing.dtos.VideoRatingDTO;
import com.videosharing.models.VideoRating;

public class VideoRatingMapper {

	public static VideoRatingDTO toDTO(VideoRating model) {
		VideoRatingDTO dto = new VideoRatingDTO();
		dto.setId(model.getId());
		dto.setVideo(VideoMapper.toDTO(model.getVideo()));
		dto.setUser(UserMapper.toDTO(model.getUser()));
		dto.setRating(model.getRating());
		dto.setRatedDate(model.getRatedDate());
		return dto;
	}

	public static List<VideoRatingDTO> toDTOList(List<VideoRating> models) {
		return models.stream().map(cat -> toDTO(cat)).collect(Collectors.toList());
	}

	public static VideoRating toModel(VideoRatingDTO dto) {
		VideoRating model = new VideoRating();
		model.setId(dto.getId());
		model.setVideo(VideoMapper.toModel(dto.getVideo()));
		model.setUser(UserMapper.toModel(dto.getUser()));
		model.setRating(dto.getRating());
		model.setRatedDate(dto.getRatedDate());
		return model;
	}

}