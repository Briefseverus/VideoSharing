package com.videosharing.dtos;

import lombok.Data;

@Data
public class VideoTagsMappingDTO {
	private Integer id;
	private int videoId;
	private int tagId;

}