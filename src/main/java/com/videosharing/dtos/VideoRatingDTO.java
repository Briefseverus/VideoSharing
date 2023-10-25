package com.videosharing.dtos;

import java.util.Date;

import lombok.Data;

@Data
public class VideoRatingDTO {
	private Integer id;
	private VideoDTO video;
	private UserDTO user;
	private Integer rating;
	private Date ratedDate;
}