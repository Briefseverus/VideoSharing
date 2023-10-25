package com.videosharing.dtos;

import lombok.Data;

@Data
public class UserUpdateDTO {
	private String username;
	private String password;
	private String email;
}