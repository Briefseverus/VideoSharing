package com.videosharing.mappers;

import java.util.List;
import java.util.stream.Collectors;

import com.videosharing.dtos.VideoDTO;
import com.videosharing.models.Video;

public class VideoMapper {

	public static VideoDTO toDTO(Video model) {
		VideoDTO dto = new VideoDTO();
		dto.setId(model.getId());
		dto.setTitle(model.getTitle());
		dto.setDescription(model.getDescription());
		dto.setVideoUrl(model.getVideoURL());
		dto.setFileName(model.getFilename());
		dto.setUploadDate(model.getUploadDate());
		dto.setDuration(model.getDuration());
		dto.setUploader(UserMapper.toDTO(model.getUploader()));
		dto.setChannel(ChannelMapper.toDTO(model.getChannel()));
		return dto;
	}

	public static List<VideoDTO> toDTOList(List<Video> models) {
		return models.stream().map(cat -> toDTO(cat)).collect(Collectors.toList());
	}

	public static Video toModel(VideoDTO dto) {
		Video model = new Video();
		model.setId(dto.getId());
		model.setTitle(dto.getTitle());
		model.setDescription(dto.getDescription());
		model.setVideoURL(dto.getVideoUrl());
		model.setFilename(dto.getFileName());
		model.setUploadDate(dto.getUploadDate());
		model.setDuration(dto.getDuration());
		model.setUploader(UserMapper.toModel(dto.getUploader()));
		model.setChannel(ChannelMapper.toModel(dto.getChannel()));
		return model;
	}

}