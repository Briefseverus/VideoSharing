package com.videosharing.dtos;

import java.util.Date;

import lombok.Data;

@Data
public class VideoDTO {
	private Integer id;
	private String title;
	private String description;
	private String videoUrl;
	private String fileName;
	private Date uploadDate;
	private Integer duration;
	private UserDTO uploader;
	private ChannelDTO channel;
}