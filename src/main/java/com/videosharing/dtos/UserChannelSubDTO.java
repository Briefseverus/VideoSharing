package com.videosharing.dtos;

import java.util.Date;

import lombok.Data;

@Data
public class UserChannelSubDTO {
	private Integer id;
	private UserDTO user;
	private ChannelDTO channel;
	private Date subscribeDate;
}