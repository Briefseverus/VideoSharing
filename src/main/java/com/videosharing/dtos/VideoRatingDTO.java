package com.videosharing.dtos;

import java.util.Date;

import lombok.Data;

@Data
public class VideoRatingDTO {
	private Integer id;
	private int videoId;
	private int userId;
	private Integer rating;
	private Date ratedDate;
}