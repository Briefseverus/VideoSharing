package com.videosharing.dtos;

import java.util.Date;

import lombok.Data;

@Data
public class UserDTO {
	private Integer id;
	private String username;
	private String password;
	private String email;
	private Date joinDate;
	private boolean enabled;
	private String role;
	private boolean isOwner;
}