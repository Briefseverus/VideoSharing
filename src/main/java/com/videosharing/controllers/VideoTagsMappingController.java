package com.videosharing.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
import org.springframework.web.bind.annotation.RestController;

import com.videosharing.configs.CustomUserDetails;
import com.videosharing.dtos.VideoTagsMappingDTO;
import com.videosharing.mappers.VideoTagsMappingMapper;
import com.videosharing.models.VideoTagsMapping;
import com.videosharing.services.VideoService;
import com.videosharing.services.VideoTagsMappingService;

@RestController
@RequestMapping("/api/video-tags-mapping")
public class VideoTagsMappingController {

	@Autowired
	private VideoTagsMappingMapper videoTagsMappingMapper;

	@Autowired
	private VideoService videoService;

	@Autowired
	private VideoTagsMappingService videoTagsMappingService;

	@GetMapping("/{id}")
	public VideoTagsMappingDTO getVideoTagsMappingById(@PathVariable Integer id) {
		return videoTagsMappingMapper.toDTO(videoTagsMappingService.getVideoTagsMappingById(id));
	}

	@GetMapping
	public List<VideoTagsMappingDTO> getAllVideoTagsMappings() {
		return videoTagsMappingMapper.toDTOList(videoTagsMappingService.getAllVideoTagsMappings());
	}

	@PostMapping
	public ResponseEntity<VideoTagsMappingDTO> createVideoTagsMapping(
			@RequestBody VideoTagsMappingDTO videoTagsMappingDTO) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		CustomUserDetails currentUserDetails = (CustomUserDetails) authentication.getPrincipal();
		if (!videoService.isOwner(videoTagsMappingDTO.getVideoId(), currentUserDetails.getUser().getId())) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
		}

		VideoTagsMapping videoTagsMapping = videoTagsMappingService
				.createVideoTagsMapping(videoTagsMappingMapper.toModel(videoTagsMappingDTO));

		return ResponseEntity.ok(videoTagsMappingMapper.toDTO(videoTagsMapping));
	}

	@PutMapping("/{id}")
	public ResponseEntity<VideoTagsMappingDTO> updateVideoTagsMapping(@PathVariable Integer id,
			@RequestBody VideoTagsMappingDTO videoTagsMappingDTO) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		CustomUserDetails currentUserDetails = (CustomUserDetails) authentication.getPrincipal();
		if (!videoService.isOwner(videoTagsMappingDTO.getVideoId(), currentUserDetails.getUser().getId())) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
		}

		VideoTagsMapping videoTagsMapping = videoTagsMappingService.updateVideoTagsMapping(id,
				videoTagsMappingMapper.toModel(videoTagsMappingDTO));

		return ResponseEntity.ok(videoTagsMappingMapper.toDTO(videoTagsMapping));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteVideoTagsMapping(@PathVariable Integer id) {
		VideoTagsMapping videoTagsMapping = videoTagsMappingService.getVideoTagsMappingById(id);
		if (videoTagsMapping == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		CustomUserDetails currentUserDetails = (CustomUserDetails) authentication.getPrincipal();
		if (!videoService.isOwner(videoTagsMapping.getVideo().getId(), currentUserDetails.getUser().getId())) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
		}

		videoTagsMappingService.deleteVideoTagsMapping(id);

		return ResponseEntity.ok().build();
	}

}
