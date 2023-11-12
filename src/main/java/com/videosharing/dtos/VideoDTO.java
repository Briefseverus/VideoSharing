package com.videosharing.dtos;

import java.util.Date;

import lombok.Data;

@Data
public class VideoDTO {
	private Integer id;
	private String title;
	private String description;
	private String fileName;
	private Date uploadDate;
	private long duration;
	private int channelId;
	private String videoUrl;
	private Integer viewCount;
	

}