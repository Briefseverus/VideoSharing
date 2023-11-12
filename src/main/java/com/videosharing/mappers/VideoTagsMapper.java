package com.videosharing.mappers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.videosharing.dtos.VideoTagsDTO;
import com.videosharing.models.VideoTags;

@Component
public class VideoTagsMapper {

	public  VideoTagsDTO toDTO(VideoTags model) {
		VideoTagsDTO dto = new VideoTagsDTO();
		dto.setId(model.getId());
		dto.setTagName(model.getTagName());
		return dto;
	}

	public  List<VideoTagsDTO> toDTOList(List<VideoTags> models) {
		return models.stream().map(cat -> toDTO(cat)).collect(Collectors.toList());
	}

	public  VideoTags toModel(VideoTagsDTO dto) {
		VideoTags model = new VideoTags();
		model.setId(dto.getId());
		model.setTagName(dto.getTagName());
		return model;
	}

}