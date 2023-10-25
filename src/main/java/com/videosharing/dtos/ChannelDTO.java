package com.videosharing.dtos;

import java.util.Date;

import lombok.Data;

@Data
public class ChannelDTO {
	private Integer id;
	private String name;
	private String description;
	private Date createDate;
	private UserBasicInfoDTO creator;
}
