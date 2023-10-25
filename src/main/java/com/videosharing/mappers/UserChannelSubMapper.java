package com.videosharing.mappers;

import java.util.List;
import java.util.stream.Collectors;

import com.videosharing.dtos.UserChannelSubDTO;
import com.videosharing.models.UserChannelSub;

public class UserChannelSubMapper {

	public static UserChannelSubDTO toDTO(UserChannelSub model) {
		UserChannelSubDTO dto = new UserChannelSubDTO();
		dto.setId(model.getId());
		dto.setUser(UserMapper.toDTO(model.getUser()));
		dto.setChannel(ChannelMapper.toDTO(model.getChannel()));
		dto.setSubscribeDate(model.getSubscribeDate());
		return dto;
	}

	public static List<UserChannelSubDTO> toDTOList(List<UserChannelSub> models) {
		return models.stream().map(cat -> toDTO(cat)).collect(Collectors.toList());
	}
	
	public static UserChannelSub toModel(UserChannelSubDTO dto) {
		UserChannelSub model = new UserChannelSub();
		model.setId(dto.getId());
		model.setUser(UserMapper.toModel(dto.getUser()));
		model.setChannel(ChannelMapper.toModel(dto.getChannel()));
		model.setSubscribeDate(dto.getSubscribeDate());
		return model;
	}

}