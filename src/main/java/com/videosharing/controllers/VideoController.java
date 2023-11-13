package com.videosharing.controllers;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.videosharing.configs.CustomUserDetails;
import com.videosharing.dtos.VideoDTO;
import com.videosharing.dtos.VideoSummaryDTO;
import com.videosharing.mappers.ChannelMapper;
import com.videosharing.mappers.VideoMapper;
import com.videosharing.models.Video;
import com.videosharing.services.ChannelService;
import com.videosharing.services.VideoService;
import com.videosharing.services.VideoViewService;

import io.jsonwebtoken.io.IOException;

@RestController
@RequestMapping("/api/videos")
public class VideoController {

	@Autowired
	private VideoService videoService;

	@Autowired
	private VideoMapper videoMapper;
	
	@Autowired
	private ChannelService channelServices;

	@Autowired
	private	ChannelMapper channelMapper;
	
	@Autowired
	private VideoViewService videoViewService;

	@PostMapping(value = "/upload",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<String> uploadVideo(@RequestPart("file") MultipartFile file,
			@RequestPart("videoDTO") VideoDTO videoDTO) throws IOException {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		CustomUserDetails currentUserDetails = (CustomUserDetails) authentication.getPrincipal();
		Integer currentUserId = currentUserDetails.getUser().getId();
		if (channelServices.isOwner(videoDTO.getChannelId(), currentUserId)) {
			Video updatedMetadata = videoService.uploadVideo(file, videoDTO);

			if (updatedMetadata != null) {
				return ResponseEntity.ok("Video uploaded successfully");
			} else {
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to upload video");
			}
		} else {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
		}

	}

	@GetMapping("/{id}")
	public VideoDTO getVideoById(@PathVariable Integer id) {
		return videoMapper.toDTO(videoService.getVideoById(id), videoViewService);
	}

	@GetMapping("/similar/{id}")
	public List<VideoSummaryDTO> getSimilarVideos(@PathVariable Integer id) {
	    Stream<Video> similarVideosStream = videoService.getSimilarVideos(id);
	    List<VideoSummaryDTO> videoSummaryDTOs = similarVideosStream
	            .map(video -> videoMapper.toSummaryDTO(video, videoViewService)).collect(Collectors.toList());
	    return videoSummaryDTOs;
	}

	@GetMapping
	public List<VideoSummaryDTO> getAllVideos() {
		return videoMapper.toSummaryDTOList(videoService.getAllVideos(), videoViewService);
	}

	@GetMapping("/categories/{categoryId}")
	public List<VideoSummaryDTO> getVideosByCategories(@PathVariable Integer categoryId) {
		Stream<Video> videosStream = videoService.getVideosByCategoriesAsStream(categoryId);
		List<VideoSummaryDTO> videoSummaryDTOs = videosStream
				.map(video -> videoMapper.toSummaryDTO(video, videoViewService)).collect(Collectors.toList());
		return videoSummaryDTOs;
	}

	@GetMapping("/tags/{tagId}")
	public List<VideoSummaryDTO> getVideosByTags(@PathVariable Integer tagId) {
		Stream<Video> videosStream = videoService.getVideosByTagsAsStream(tagId);
		List<VideoSummaryDTO> videoSummaryDTOs = videosStream
				.map(video -> videoMapper.toSummaryDTO(video, videoViewService)).collect(Collectors.toList());
		return videoSummaryDTOs;
	}


	@GetMapping("/titles/{title}")
	public List<VideoSummaryDTO> getVideosByTitles(@PathVariable String title) {
		Stream<Video> videosStream = videoService.getVideosByTitleAsStream(title);
		List<VideoSummaryDTO> videoSummaryDTOs = videosStream
				.map(video -> videoMapper.toSummaryDTO(video, videoViewService)).collect(Collectors.toList());
		return videoSummaryDTOs;
	}

	@GetMapping("/channels/{channelId}")
	public List<VideoSummaryDTO> getVideosByChannels(@PathVariable Integer channelId) {
		Stream<Video> videosStream = videoService.getVideosByChannelAsStream(channelId);
		List<VideoSummaryDTO> videoSummaryDTOs = videosStream
				.map(video -> videoMapper.toSummaryDTO(video, videoViewService)).collect(Collectors.toList());
		return videoSummaryDTOs;
	}

	@PutMapping("/{id}")
	public ResponseEntity<VideoDTO> updateVideo(@PathVariable Integer id, @RequestBody VideoDTO videoDTO) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		CustomUserDetails currentUserDetails = (CustomUserDetails) authentication.getPrincipal();
		Integer currentUserId = currentUserDetails.getUser().getId();

		if (videoService.isOwner(id, currentUserId)) {

			Video updatedVideo = videoService.updateVideo(id, videoMapper.toModel(videoDTO));
			VideoDTO updatedVideoDTO = videoMapper.toDTO(updatedVideo, videoViewService);
			return ResponseEntity.ok(updatedVideoDTO);
		} else {

			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteVideo(@PathVariable Integer id) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		CustomUserDetails currentUserDetails = (CustomUserDetails) authentication.getPrincipal();
		Integer currentUserId = currentUserDetails.getUser().getId();

		if (videoService.isOwner(id, currentUserId)) {

			videoService.deleteVideo(id);
			return ResponseEntity.noContent().build();
		} else {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}
	}

}