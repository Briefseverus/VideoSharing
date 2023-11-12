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
import com.videosharing.dtos.VideoCategoriesDTO;
import com.videosharing.mappers.VideoCategoriesMapper;
import com.videosharing.models.VideoCategories;
import com.videosharing.services.VideoCategoriesService;
import com.videosharing.services.VideoService;

@RestController
@RequestMapping("/api/video-categories-mapping")
public class VideoCategoriesController {

	@Autowired
	private VideoCategoriesMapper videoCategoriesMapper;

	@Autowired
	private VideoService videoService;

	@Autowired
	private VideoCategoriesService videoCategoriesService;

	@GetMapping("/{id}")
	public VideoCategoriesDTO getVideoCategoriesById(@PathVariable Integer id) {
		return videoCategoriesMapper.toDTO(videoCategoriesService.getVideoCategoriesById(id));
	}

	@GetMapping
	public List<VideoCategoriesDTO> getAllVideoCategoriess() {
		return videoCategoriesMapper.toDTOList(videoCategoriesService.getAllVideoCategories());
	}

	@PostMapping
	public ResponseEntity<VideoCategoriesDTO> createVideoCategories(
			@RequestBody VideoCategoriesDTO videoCategoriesDTO) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		CustomUserDetails currentUserDetails = (CustomUserDetails) authentication.getPrincipal();
		if (!videoService.isOwner(videoCategoriesDTO.getVideoId(), currentUserDetails.getUser().getId())) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
		}

		VideoCategories videoCategories = videoCategoriesService
				.createVideoCategories(videoCategoriesMapper.toModel(videoCategoriesDTO));

		return ResponseEntity.ok(videoCategoriesMapper.toDTO(videoCategories));
	}

	@PutMapping("/{id}")
	public ResponseEntity<VideoCategoriesDTO> updateVideoCategories(@PathVariable Integer id,
			@RequestBody VideoCategoriesDTO videoCategoriesDTO) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		CustomUserDetails currentUserDetails = (CustomUserDetails) authentication.getPrincipal();
		if (!videoService.isOwner(videoCategoriesDTO.getVideoId(), currentUserDetails.getUser().getId())) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
		}
		VideoCategories videoCategories = videoCategoriesService.updateVideoCategories(id,
				videoCategoriesMapper.toModel(videoCategoriesDTO));
		return ResponseEntity.ok(videoCategoriesMapper.toDTO(videoCategories));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteVideoCategories(@PathVariable Integer videoCategoriesId) {

		VideoCategories videoCategories = videoCategoriesService.getVideoCategoriesById(videoCategoriesId);
		if (videoCategories == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		CustomUserDetails currentUserDetails = (CustomUserDetails) authentication.getPrincipal();
		if (!videoService.isOwner(videoCategories.getVideo().getId(), currentUserDetails.getUser().getId())) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
		}
		videoCategoriesService.deleteVideoCategories(videoCategoriesId);

		return ResponseEntity.ok().build();
	}

}