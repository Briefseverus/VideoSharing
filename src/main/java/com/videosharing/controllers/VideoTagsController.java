package com.videosharing.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.videosharing.dtos.VideoTagsDTO;
import com.videosharing.mappers.VideoTagsMapper;
import com.videosharing.services.VideoTagsService;

@RestController
@RequestMapping("/api/video-tags")
public class VideoTagsController {

	@Autowired
	private VideoTagsMapper videoTagsMapper;
	
	@Autowired
	private VideoTagsService videoTagsService;

	@GetMapping("/{id}")
	public VideoTagsDTO getVideoTagsById(@PathVariable Integer id) {
		return videoTagsMapper.toDTO(videoTagsService.getVideoTagsById(id));
	}

	@GetMapping
	public List<VideoTagsDTO> getAllVideoTags() {
		return videoTagsMapper.toDTOList(videoTagsService.getAllVideoTags());
	}


}