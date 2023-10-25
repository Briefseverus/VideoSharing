package com.videosharing.mappers;

import java.util.List;
import java.util.stream.Collectors;

import com.videosharing.dtos.ChannelDTO;
import com.videosharing.dtos.UserBasicInfoDTO;
import com.videosharing.models.Channel;
import com.videosharing.models.User;

public class ChannelMapper {

	public static ChannelDTO toDTO(Channel model) {

		ChannelDTO dto = new ChannelDTO();
		dto.setId(model.getId());
		dto.setName(model.getName());
		dto.setCreateDate(model.getCreateDate());
		dto.setDescription(model.getDescription());
		
		User creator = model.getCreator();
		UserBasicInfoDTO creatorInfo = new UserBasicInfoDTO();
		creatorInfo.setId(creator.getId());
		creatorInfo.setUsername(creator.getUsername());
		dto.setCreator(creatorInfo);

		return dto;
	}

	public static List<ChannelDTO> toDTOList(List<Channel> models) {
		return models.stream().map(cat -> toDTO(cat)).collect(Collectors.toList());
	}

	public static Channel toModel(ChannelDTO dto) {

		Channel model = new Channel();
		model.setId(dto.getId());
		model.setName(dto.getName());
		model.setDescription(dto.getDescription());
		model.setCreateDate(dto.getCreateDate());

		

		return model;
	}
}
