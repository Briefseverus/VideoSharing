package com.videosharing.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.videosharing.dtos.VideoTagsMappingDTO;
import com.videosharing.mappers.VideoTagsMappingMapper;
import com.videosharing.services.VideoTagsMappingService;

@RestController
@RequestMapping("/api/video-tags-mapping")
public class VideoTagsMappingController {

	@Autowired
	private VideoTagsMappingService videoTagsMappingService;

	@GetMapping("/{id}")
	public VideoTagsMappingDTO getVideoTagsMappingById(@PathVariable Integer id) {
		return VideoTagsMappingMapper.toDTO(videoTagsMappingService.getVideoTagsMappingById(id));
	}

	@GetMapping
	public List<VideoTagsMappingDTO> getAllVideoTagsMappings() {
		return VideoTagsMappingMapper.toDTOList(videoTagsMappingService.getAllVideoTagsMappings());
	}

	@PostMapping
	public VideoTagsMappingDTO createVideoTagsMapping(@RequestBody VideoTagsMappingDTO videoTagsMappingDTO) {
		return VideoTagsMappingMapper.toDTO(
				videoTagsMappingService.createVideoTagsMapping(VideoTagsMappingMapper.toModel(videoTagsMappingDTO)));
	}

	@PutMapping("/{id}")
	public VideoTagsMappingDTO updateVideoTagsMapping(@PathVariable Integer id,
			@RequestBody VideoTagsMappingDTO videoTagsMappingDTO) {
		return VideoTagsMappingMapper.toDTO(videoTagsMappingService.updateVideoTagsMapping(id,
				VideoTagsMappingMapper.toModel(videoTagsMappingDTO)));
	}

	@DeleteMapping("/{id}")
	public void deleteVideoTagsMapping(@PathVariable Integer id) {
		videoTagsMappingService.deleteVideoTagsMapping(id);
	}

}