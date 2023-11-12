package com.videosharing.mappers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.videosharing.dtos.CategoriesDTO;
import com.videosharing.models.Categories;

@Component
public class CategoriesMapper {

	public  CategoriesDTO toDTO(Categories model) {
		CategoriesDTO dto = new CategoriesDTO();
		dto.setId(model.getId());
		dto.setName(model.getName());
		return dto;
	}

	public  List<CategoriesDTO> toDTOList(List<Categories> models) {
		return models.stream().map(cat -> toDTO(cat)).collect(Collectors.toList());
	}

	public  Categories toModel(CategoriesDTO dto) {
		Categories model = new Categories();
		model.setId(dto.getId());
		model.setName(dto.getName());
		return model;
	}
}