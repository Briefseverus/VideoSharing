package com.videosharing.mappers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.videosharing.dtos.ChannelBasicInfoDTO;
import com.videosharing.dtos.VideoDTO;
import com.videosharing.dtos.VideoSummaryDTO;
import com.videosharing.models.Channel;
import com.videosharing.models.Video;
import com.videosharing.services.ChannelService;
import com.videosharing.services.VideoViewService;

@Component
public class VideoMapper {

	@Autowired
	private ChannelService channelService;

	public VideoDTO toDTO(Video model, VideoViewService videoViewService) {

		Integer viewCount = videoViewService.getTotalViewsForVideo(model.getId());
		VideoDTO dto = new VideoDTO();
		dto.setId(model.getId());
		dto.setTitle(model.getTitle());
		dto.setDescription(model.getDescription());
		dto.setFileName(model.getFilename());
		dto.setUploadDate(model.getUploadDate());
		dto.setDuration(model.getDuration());
		dto.setVideoUrl(model.getVideoUrl());
		dto.setChannelId(model.getChannel().getId());
		dto.setViewCount(viewCount);
		return dto;
	}

	public VideoDTO toDTO(Video model) {

		VideoDTO dto = new VideoDTO();
		dto.setId(model.getId());
		dto.setTitle(model.getTitle());
		dto.setDescription(model.getDescription());
		dto.setFileName(model.getFilename());
		dto.setUploadDate(model.getUploadDate());
		dto.setDuration(model.getDuration());
		dto.setVideoUrl(model.getVideoUrl());
		dto.setChannelId(model.getChannel().getId());

		return dto;
	}

	public List<VideoDTO> toDTOList(List<Video> models, VideoViewService videoViewService) {
		return models.stream().map(video -> toDTO(video, videoViewService)).collect(Collectors.toList());
	}

	public Video toModel(VideoDTO dto) {
		Video model = new Video();
		model.setId(dto.getId());
		model.setTitle(dto.getTitle());
		model.setDescription(dto.getDescription());
		model.setFilename(dto.getFileName());
		model.setUploadDate(dto.getUploadDate());
		model.setDuration(dto.getDuration());
		model.setVideoUrl(dto.getVideoUrl());
		model.setChannel(channelService.getChannelById(dto.getChannelId()));

		return model;
	}

	public VideoSummaryDTO toSummaryDTO(Video model, VideoViewService videoViewService) {

		VideoSummaryDTO dto = new VideoSummaryDTO();

		dto.setId(model.getId());

		dto.setTitle(model.getTitle());

		dto.setDescription(model.getDescription());

		dto.setDuration(model.getDuration());

		Integer viewCount = videoViewService.getTotalViewsForVideo(model.getId());

		dto.setViewCount(viewCount);
		Channel channel = model.getChannel();
		ChannelBasicInfoDTO channelBasicInfoDTO = new ChannelBasicInfoDTO();
		channelBasicInfoDTO.setId(channel.getId());
		channelBasicInfoDTO.setName(channel.getName());
		dto.setChannel(channelBasicInfoDTO);

		return dto;

	}

	public List<VideoSummaryDTO> toSummaryDTOList(List<Video> models, VideoViewService videoViewService) {

		return models.stream().map(cat -> toSummaryDTO(cat, videoViewService)).collect(Collectors.toList());

	}

}