package com.videosharing.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.videosharing.dtos.CategoriesDTO;
import com.videosharing.mappers.CategoriesMapper;
import com.videosharing.services.CategoriesService;

@RestController
@RequestMapping("/api/categories")
public class CategoriesController {

	@Autowired
	private CategoriesService categoriesService;

	@GetMapping("/{id}")
	public CategoriesDTO getCategoryById(@PathVariable Integer id) {
		return CategoriesMapper.toDTO(categoriesService.getCategoryById(id));
	}

	@GetMapping
	public List<CategoriesDTO> getAllCategories() {
		return CategoriesMapper.toDTOList(categoriesService.getAllCategories());
	}

	

}