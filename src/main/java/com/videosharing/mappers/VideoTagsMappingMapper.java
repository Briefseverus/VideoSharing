package com.videosharing.mappers;

import java.util.List;
import java.util.stream.Collectors;

import com.videosharing.dtos.VideoTagsMappingDTO;
import com.videosharing.models.VideoTagsMapping;

public class VideoTagsMappingMapper {

	public static VideoTagsMappingDTO toDTO(VideoTagsMapping model) {
		VideoTagsMappingDTO dto = new VideoTagsMappingDTO();
		dto.setId(model.getId());
		dto.setVideo(VideoMapper.toDTO(model.getVideo()));
		dto.setTag(VideoTagsMapper.toDTO(model.getTag()));
		return dto;
	}

	public static List<VideoTagsMappingDTO> toDTOList(List<VideoTagsMapping> models) {
		return models.stream().map(cat -> toDTO(cat)).collect(Collectors.toList());
	}

	public static VideoTagsMapping toModel(VideoTagsMappingDTO dto) {
		VideoTagsMapping model = new VideoTagsMapping();
		model.setId(dto.getId());
		model.setVideo(VideoMapper.toModel(dto.getVideo()));
		model.setTag(VideoTagsMapper.toModel(dto.getTag()));
		return model;
	}

}