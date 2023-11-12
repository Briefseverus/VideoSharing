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
	private VideoTagsMappingMapper videoTagsMappingMapper;
	
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
	public VideoTagsMappingDTO createVideoTagsMapping(@RequestBody VideoTagsMappingDTO videoTagsMappingDTO) {
		return videoTagsMappingMapper.toDTO(
				videoTagsMappingService.createVideoTagsMapping(videoTagsMappingMapper.toModel(videoTagsMappingDTO)));
	}

	@PutMapping("/{id}")
	public VideoTagsMappingDTO updateVideoTagsMapping(@PathVariable Integer id,
			@RequestBody VideoTagsMappingDTO videoTagsMappingDTO) {
		return videoTagsMappingMapper.toDTO(videoTagsMappingService.updateVideoTagsMapping(id,
				videoTagsMappingMapper.toModel(videoTagsMappingDTO)));
	}

	@DeleteMapping("/{id}")
	public void deleteVideoTagsMapping(@PathVariable Integer id) {
		videoTagsMappingService.deleteVideoTagsMapping(id);
	}

}