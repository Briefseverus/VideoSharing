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

import com.videosharing.dtos.VideoDTO;
import com.videosharing.mappers.VideoMapper;
import com.videosharing.services.VideoService;

@RestController
@RequestMapping("/api/videos")
public class VideoController {

	@Autowired
	private VideoService videoService;

	@GetMapping("/{id}")
	public VideoDTO getVideoById(@PathVariable Integer id) {
		return VideoMapper.toDTO(videoService.getVideoById(id));
	}

	@GetMapping
	public List<VideoDTO> getAllVideos() {
		return VideoMapper.toDTOList(videoService.getAllVideos());
	}

	@PostMapping
	public VideoDTO createVideo(@RequestBody VideoDTO videoDTO) {
		return VideoMapper.toDTO(videoService.createVideo(VideoMapper.toModel(videoDTO)));
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