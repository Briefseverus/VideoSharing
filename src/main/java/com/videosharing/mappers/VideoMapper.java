package com.videosharing.mappers;

import java.util.List;
import java.util.stream.Collectors;

import com.videosharing.dtos.ChannelBasicInfoDTO;
import com.videosharing.dtos.UserBasicInfoDTO;
import com.videosharing.dtos.VideoDTO;
import com.videosharing.dtos.VideoSummaryDTO;
import com.videosharing.models.Channel;
import com.videosharing.models.User;
import com.videosharing.models.Video;
import com.videosharing.services.VideoViewService;

public class VideoMapper {

	public static VideoDTO toDTO(Video model ) {
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
		dto.setFirebaseId(model.getFirebaseId());
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
		model.setFirebaseId(dto.getFirebaseId());
		return model;
	}

	public static VideoSummaryDTO toSummaryDTO(Video model, VideoViewService videoViewService) {

		VideoSummaryDTO dto = new VideoSummaryDTO();

		dto.setId(model.getId());

		dto.setTitle(model.getTitle());

		dto.setDescription(model.getDescription());

		dto.setVideoUrl(model.getVideoURL());

		dto.setDuration(model.getDuration());
		
		dto.setFirebaseId(model.getFirebaseId());

		Integer viewCount =  videoViewService.getTotalViewsForVideo(model.getId());

		
		dto.setViewCount(viewCount);
		
		User creator = model.getUploader();
		UserBasicInfoDTO creatorInfo = new UserBasicInfoDTO();
		creatorInfo.setId(creator.getId());
		creatorInfo.setUsername(creator.getUsername());
		dto.setUploader(creatorInfo);
		Channel channel = model.getChannel();
		ChannelBasicInfoDTO channelBasicInfoDTO = new ChannelBasicInfoDTO();
		channelBasicInfoDTO.setId(channel.getId());
		channelBasicInfoDTO.setName(channel.getName());
		dto.setChannel(channelBasicInfoDTO);
		

		return dto;

	}

	public static List<VideoSummaryDTO> toSummaryDTOList(List<Video> models, VideoViewService videoViewService) {

		return models.stream().map(cat -> toSummaryDTO(cat, videoViewService)).collect(Collectors.toList());

	}

}