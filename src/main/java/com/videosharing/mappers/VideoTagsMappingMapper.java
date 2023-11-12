package com.videosharing.mappers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.videosharing.dtos.VideoTagsMappingDTO;
import com.videosharing.models.VideoTagsMapping;
import com.videosharing.services.VideoService;
import com.videosharing.services.VideoTagsService;

@Component
public class VideoTagsMappingMapper {
	@Autowired
	private VideoService videoService;
	
	@Autowired
	private VideoTagsService tagService;

	public  VideoTagsMappingDTO toDTO(VideoTagsMapping model) {
		VideoTagsMappingDTO dto = new VideoTagsMappingDTO();
		dto.setId(model.getId());
		dto.setVideoId(model.getVideo().getId());
		dto.setTagId(model.getTag().getId());
		return dto;
	}

	public  List<VideoTagsMappingDTO> toDTOList(List<VideoTagsMapping> models) {
		return models.stream().map(cat -> toDTO(cat)).collect(Collectors.toList());
	}

	public  VideoTagsMapping toModel(VideoTagsMappingDTO dto) {
		VideoTagsMapping model = new VideoTagsMapping();
		model.setId(dto.getId());
		model.setVideo(videoService.getVideoById(dto.getVideoId()));
		model.setTag(tagService.getVideoTagsById(dto.getTagId()));
		return model;
	}

}