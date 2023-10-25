package com.videosharing.mappers;

import java.util.List;
import java.util.stream.Collectors;

import com.videosharing.dtos.UserDTO;
import com.videosharing.models.User;

public class UserMapper {

	public static UserDTO toDTO(User model) {
		UserDTO dto = new UserDTO();
		dto.setId(model.getId());
		dto.setUsername(model.getUsername());
		dto.setPassword(model.getPassword());
		dto.setEmail(model.getEmail());
		dto.setJoinDate(model.getJoinDate());
		dto.setRole(model.getRole());
		dto.setEnabled(model.isEnabled());
		return dto;
	}

	public static List<UserDTO> toDTOList(List<User> models) {
		return models.stream().map(cat -> toDTO(cat)).collect(Collectors.toList());
	}

	public static User toModel(UserDTO dto) {
		User model = new User();
		model.setId(dto.getId());
		model.setUsername(dto.getUsername());
		model.setPassword(dto.getPassword());
		model.setEmail(dto.getEmail());
		model.setJoinDate(dto.getJoinDate());
		model.setRole(dto.getRole());
		model.setEnabled(dto.isEnabled());
		return model;
	}
}