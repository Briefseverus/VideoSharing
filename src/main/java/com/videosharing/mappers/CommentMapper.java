package com.videosharing.mappers;

import java.util.List;
import java.util.stream.Collectors;

import com.videosharing.dtos.CommentDTO;
import com.videosharing.models.Comment;

public class CommentMapper {

	public static CommentDTO toDTO(Comment model) {
		CommentDTO dto = new CommentDTO();
		dto.setId(model.getId());
		dto.setVideo(VideoMapper.toDTO(model.getVideo()));
		dto.setUser(UserMapper.toDTO(model.getUser()));
		dto.setContent(model.getContent());
		dto.setPostDate(model.getPostDate());
		return dto;
	}

	public static List<CommentDTO> toDTOList(List<Comment> models) {
		return models.stream().map(cat -> toDTO(cat)).collect(Collectors.toList());
	}
	
	public static Comment toModel(CommentDTO dto) {
		Comment model = new Comment();
		model.setId(dto.getId());
		model.setVideo(VideoMapper.toModel(dto.getVideo()));
		model.setUser(UserMapper.toModel(dto.getUser()));
		model.setContent(dto.getContent());
		model.setPostDate(dto.getPostDate());
		return model;
	}
}