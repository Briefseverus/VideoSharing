package com.videosharing.dtos;

import lombok.Data;

@Data
public class VideoSummaryDTO {

	private Integer id;

	private String title;

	private String description;

	private String videoUrl;

	private Integer duration;

	private Integer viewCount;

	private UserBasicInfoDTO uploader;

	private ChannelBasicInfoDTO channel;
	private String firebaseId;

}