package com.videosharing.mappers;

import java.util.List;
import java.util.stream.Collectors;

import com.videosharing.dtos.VideoCategoriesDTO;
import com.videosharing.models.VideoCategories;

public class VideoCategoriesMapper {

	public static VideoCategoriesDTO toDTO(VideoCategories model) {
		VideoCategoriesDTO dto = new VideoCategoriesDTO();
		dto.setId(model.getId());
		dto.setVideo(VideoMapper.toDTO(model.getVideo()));
		dto.setCategories(CategoriesMapper.toDTO(model.getCategories()));
		return dto;
	}

	public static List<VideoCategoriesDTO> toDTOList(List<VideoCategories> models) {
		return models.stream().map(cat -> toDTO(cat)).collect(Collectors.toList());
	}

	public static VideoCategories toModel(VideoCategoriesDTO dto) {
		VideoCategories model = new VideoCategories();
		model.setId(dto.getId());
		model.setVideo(VideoMapper.toModel(dto.getVideo()));
		model.setCategories(CategoriesMapper.toModel(dto.getCategories()));
		return model;
	}

}