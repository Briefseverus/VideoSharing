package com.videosharing.dtos;

import java.util.Date;

import lombok.Data;

@Data
public class CommentDTO {
	private Integer id;
	private int videoId;
	private int userId;
	private String content;
	private Date postDate;
}