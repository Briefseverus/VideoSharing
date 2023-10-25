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

import com.videosharing.dtos.VideoCategoriesDTO;
import com.videosharing.mappers.VideoCategoriesMapper;
import com.videosharing.services.VideoCategoriesService;

@RestController
@RequestMapping("/api/video-categories-mapping")
public class VideoCategoriesController {

	@Autowired
	private VideoCategoriesService videoCategoriesService;

	@GetMapping("/{id}")
	public VideoCategoriesDTO getVideoCategoriesById(@PathVariable Integer id) {
		return VideoCategoriesMapper.toDTO(videoCategoriesService.getVideoCategoriesById(id));
	}

	@GetMapping
	public List<VideoCategoriesDTO> getAllVideoCategoriess() {
		return VideoCategoriesMapper.toDTOList(videoCategoriesService.getAllVideoCategoriess());
	}

	@PostMapping
	public VideoCategoriesDTO createVideoCategories(@RequestBody VideoCategoriesDTO videoCategoriesDTO) {
		return VideoCategoriesMapper.toDTO(
				videoCategoriesService.createVideoCategories(VideoCategoriesMapper.toModel(videoCategoriesDTO)));
	}

	@PutMapping("/{id}")
	public VideoCategoriesDTO updateVideoCategories(@PathVariable Integer id,
			@RequestBody VideoCategoriesDTO videoCategoriesDTO) {
		return VideoCategoriesMapper.toDTO(
				videoCategoriesService.updateVideoCategories(id, VideoCategoriesMapper.toModel(videoCategoriesDTO)));
	}

	@DeleteMapping("/{id}")
	public void deleteVideoCategories(@PathVariable Integer id) {
		videoCategoriesService.deleteVideoCategories(id);
	}

}