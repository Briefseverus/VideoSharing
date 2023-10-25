package com.videosharing.dtos;

import java.util.Date;

import lombok.Data;

@Data
public class VideoViewDTO {
	private Integer id;
	private VideoDTO video;
	private String viewerIp;
	private Date viewDatetime;

}