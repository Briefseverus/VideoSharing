package com.videosharing.mappers;

import java.util.List;
import java.util.stream.Collectors;

import com.videosharing.dtos.VideoTagsDTO;
import com.videosharing.models.VideoTags;

public class VideoTagsMapper {

	public static VideoTagsDTO toDTO(VideoTags model) {
		VideoTagsDTO dto = new VideoTagsDTO();
		dto.setId(model.getId());
		dto.setTagName(model.getTagName());
		return dto;
	}

	public static List<VideoTagsDTO> toDTOList(List<VideoTags> models) {
		return models.stream().map(cat -> toDTO(cat)).collect(Collectors.toList());
	}

	public static VideoTags toModel(VideoTagsDTO dto) {
		VideoTags model = new VideoTags();
		model.setId(dto.getId());
		model.setTagName(dto.getTagName());
		return model;
	}

}