package com.videosharing.mappers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.videosharing.dtos.VideoViewDTO;
import com.videosharing.models.VideoView;
import com.videosharing.services.VideoService;

@Component
public class VideoViewMapper {
	@Autowired
	private VideoService videoService;
	
	public  VideoViewDTO toDTO(VideoView model) {
		VideoViewDTO dto = new VideoViewDTO();
		dto.setId(model.getId());
		dto.setVideoId(model.getVideo().getId());
		dto.setViewerIp(model.getViewerIp());
		dto.setViewDatetime(model.getViewDatetime());
		return dto;
	}

	public  List<VideoViewDTO> toDTOList(List<VideoView> models) {
		return models.stream().map(cat -> toDTO(cat)).collect(Collectors.toList());
	}

	public  VideoView toModel(VideoViewDTO dto) {
		VideoView model = new VideoView();
		model.setId(dto.getId());
		model.setVideo(videoService.getVideoById(dto.getVideoId()));
		model.setViewerIp(dto.getViewerIp());
		model.setViewDatetime(dto.getViewDatetime());
		return model;
	}
}