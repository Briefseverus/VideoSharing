package com.videosharing.controllers;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.videosharing.configs.CustomUserDetails;
import com.videosharing.dtos.VideoDTO;
import com.videosharing.dtos.VideoSummaryDTO;
import com.videosharing.mappers.VideoMapper;
import com.videosharing.models.Channel;
import com.videosharing.models.User;
import com.videosharing.models.Video;
import com.videosharing.services.ChannelService;
import com.videosharing.services.VideoService;
import com.videosharing.services.VideoViewService;

@RestController
@RequestMapping("/api/videos")
public class VideoController {

	@Autowired
	private VideoService videoService;

	@Autowired
	private ChannelService channelServices;
	
	@Autowired
	VideoViewService videoViewService;

	@GetMapping("/{id}")
	public VideoSummaryDTO getVideoById(@PathVariable Integer id) {
		return VideoMapper.toSummaryDTO(videoService.getVideoById(id), videoViewService);
	}

	@GetMapping
	public List<VideoSummaryDTO> getAllVideos() {
		return VideoMapper.toSummaryDTOList(videoService.getAllVideos(), videoViewService);
	}

	@GetMapping("/categories/{categoryId}")
	public List<VideoSummaryDTO> getVideosByCategories(@PathVariable Integer categoryId) {
		Stream<Video> videosStream = videoService.getVideosByCategoriesAsStream(categoryId);
		List<VideoSummaryDTO> videoSummaryDTOs = videosStream
				.map(video -> VideoMapper.toSummaryDTO(video, videoViewService)).collect(Collectors.toList());
		return videoSummaryDTOs;
	}

	@GetMapping("/tags/{tagId}")
	public List<VideoSummaryDTO> getVideosByTags(@PathVariable Integer tagId) {
		Stream<Video> videosStream = videoService.getVideosByTagsAsStream(tagId);
		List<VideoSummaryDTO> videoSummaryDTOs = videosStream
				.map(video -> VideoMapper.toSummaryDTO(video, videoViewService)).collect(Collectors.toList());
		return videoSummaryDTOs;
	}

	@GetMapping("/titles/{title}")
	public List<VideoSummaryDTO> getVideosByTitles(@PathVariable String title) {
		Stream<Video> videosStream = videoService.getVideosByTitleAsStream(title);
		List<VideoSummaryDTO> videoSummaryDTOs = videosStream
				.map(video -> VideoMapper.toSummaryDTO(video, videoViewService)).collect(Collectors.toList());
		return videoSummaryDTOs;
	}

	@GetMapping("/channels/{channelId}")
	public List<VideoSummaryDTO> getVideosByChannels(@PathVariable Integer channelId) {
		Stream<Video> videosStream = videoService.getVideosByChannelAsStream(channelId);
		List<VideoSummaryDTO> videoSummaryDTOs = videosStream
				.map(video -> VideoMapper.toSummaryDTO(video, videoViewService)).collect(Collectors.toList());
		return videoSummaryDTOs;
	}


	@PostMapping
	public VideoDTO createVideo(@RequestBody VideoDTO videoDTO,Authentication authentication) {
		CustomUserDetails currentUserDetails = (CustomUserDetails) authentication.getPrincipal();
		User currentUser = currentUserDetails.getUser();
		Channel channel = channelServices.findByCreatorId(currentUser.getId());
		Video video = VideoMapper.toModel(videoDTO);
		video.setChannel(channel);
		video.setUploader(currentUser);
		return VideoMapper.toDTO(videoService.createVideo(video));
	}

	@PutMapping("/{id}")
	public VideoDTO updateVideo(@PathVariable Integer id, @RequestBody VideoDTO videoDTO) {
		return VideoMapper.toDTO(videoService.updateVideo(id, VideoMapper.toModel(videoDTO)));
	}

	@DeleteMapping("/{id}")
	public void deleteVideo(@PathVariable Integer id) {
		videoService.deleteVideo(id);
	}

}