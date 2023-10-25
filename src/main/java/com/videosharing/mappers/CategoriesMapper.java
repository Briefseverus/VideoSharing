package com.videosharing.mappers;

import java.util.List;
import java.util.stream.Collectors;

import com.videosharing.dtos.CategoriesDTO;
import com.videosharing.models.Categories;

public class CategoriesMapper {

	public static CategoriesDTO toDTO(Categories model) {
		CategoriesDTO dto = new CategoriesDTO();
		dto.setId(model.getId());
		dto.setName(model.getName());
		return dto;
	}

	public static List<CategoriesDTO> toDTOList(List<Categories> models) {
		return models.stream().map(cat -> toDTO(cat)).collect(Collectors.toList());
	}

	public static Categories toModel(CategoriesDTO dto) {
		Categories model = new Categories();
		model.setId(dto.getId());
		model.setName(dto.getName());
		return model;
	}
}