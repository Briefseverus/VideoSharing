package com.videosharing.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.videosharing.dtos.VideoViewDTO;
import com.videosharing.mappers.VideoViewMapper;
import com.videosharing.services.VideoViewService;

@RestController
@RequestMapping("/api/video-views")
public class VideoViewController {

	@Autowired
	private VideoViewService videoViewService;

	@GetMapping("/{id}")
	public VideoViewDTO getVideoViewById(@PathVariable Integer id) {
		return VideoViewMapper.toDTO(videoViewService.getVideoViewById(id));
	}

	@GetMapping
	public List<VideoViewDTO> getAllVideoViews() {
		return VideoViewMapper.toDTOList(videoViewService.getAllVideoViews());
	}

	@PostMapping
	public VideoViewDTO createVideoView(@RequestBody VideoViewDTO videoViewDTO) {
		return VideoViewMapper.toDTO(videoViewService.createVideoView(VideoViewMapper.toModel(videoViewDTO)));
	}

}