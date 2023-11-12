package com.videosharing.mappers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.videosharing.dtos.VideoCategoriesDTO;
import com.videosharing.models.VideoCategories;
import com.videosharing.services.CategoriesService;
import com.videosharing.services.VideoService;

@Component
public class VideoCategoriesMapper {
	@Autowired
	private VideoService videoService;

	@Autowired
	private CategoriesService categoriesService;

	public VideoCategoriesDTO toDTO(VideoCategories model) {
		VideoCategoriesDTO dto = new VideoCategoriesDTO();
		dto.setId(model.getId());
		dto.setVideoId(model.getVideo().getId());
		dto.setCategoriesId(model.getCategories().getId());
		return dto;
	}

	public List<VideoCategoriesDTO> toDTOList(List<VideoCategories> models) {
		return models.stream().map(cat -> toDTO(cat)).collect(Collectors.toList());
	}

	public VideoCategories toModel(VideoCategoriesDTO dto) {
		VideoCategories model = new VideoCategories();
		model.setId(dto.getId());
		model.setVideo(videoService.getVideoById(dto.getVideoId()));
		model.setCategories(categoriesService.getCategoryById(dto.getCategoriesId()));
		return model;
	}

}