package com.videosharing.dtos;

import lombok.Data;

@Data
public class VideoSummaryDTO {

	private Integer id;

	private String title;

	private String description;

	

	private long duration;

	private Integer viewCount;


	private ChannelBasicInfoDTO channel;

}