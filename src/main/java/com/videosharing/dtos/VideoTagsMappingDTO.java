package com.videosharing.dtos;

import lombok.Data;

@Data
public class VideoTagsMappingDTO {
	private Integer id;
	private VideoDTO video;
	private VideoTagsDTO tag;

}