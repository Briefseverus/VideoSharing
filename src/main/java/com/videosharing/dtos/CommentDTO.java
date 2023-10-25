package com.videosharing.dtos;

import java.util.Date;

import lombok.Data;

@Data
public class CommentDTO {
	private Integer id;
	private VideoDTO video;
	private UserDTO user;
	private String content;
	private Date postDate;
}