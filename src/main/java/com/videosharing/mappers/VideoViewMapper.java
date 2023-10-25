package com.videosharing.mappers;

import java.util.List;
import java.util.stream.Collectors;

import com.videosharing.dtos.VideoViewDTO;
import com.videosharing.models.VideoView;

public class VideoViewMapper {

	public static VideoViewDTO toDTO(VideoView model) {
		VideoViewDTO dto = new VideoViewDTO();
		dto.setId(model.getId());
		dto.setVideo(VideoMapper.toDTO(model.getVideo()));
		dto.setViewerIp(model.getViewerIp());
		dto.setViewDatetime(model.getViewDatetime());
		return dto;
	}

	public static List<VideoViewDTO> toDTOList(List<VideoView> models) {
		return models.stream().map(cat -> toDTO(cat)).collect(Collectors.toList());
	}

	public static VideoView toModel(VideoViewDTO dto) {
		VideoView model = new VideoView();
		model.setId(dto.getId());
		model.setVideo(VideoMapper.toModel(dto.getVideo()));
		model.setViewerIp(dto.getViewerIp());
		model.setViewDatetime(dto.getViewDatetime());
		return model;
	}
}