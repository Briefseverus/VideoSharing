package com.videosharing.mappers;

import com.videosharing.dtos.UserUpdateDTO;
import com.videosharing.models.User;

public class UserUpdateMapper {

	public static User toModel(UserUpdateDTO dto) {
		User model = new User();

		model.setUsername(dto.getUsername());
		model.setPassword(dto.getPassword());
		model.setEmail(dto.getEmail());

		return model;
	}

}
