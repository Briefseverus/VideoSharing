package com.videosharing.mappers;

import org.springframework.stereotype.Component;

import com.videosharing.dtos.UserUpdateDTO;
import com.videosharing.models.User;

@Component
public class UserUpdateMapper {

	public  User toModel(UserUpdateDTO dto) {
		User model = new User();
		model.setUsername(dto.getUsername());
		model.setPassword(dto.getPassword());
		model.setEmail(dto.getEmail());
		return model;
	}

}
