package com.videosharing.mappers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.videosharing.dtos.CommentDTO;
import com.videosharing.models.Comment;
import com.videosharing.services.UserService;
import com.videosharing.services.VideoService;

@Component
public class CommentMapper {
	@Autowired
	private  UserService userService;

	@Autowired
	private  VideoService videoService;


	public  CommentDTO toDTO(Comment model) {
		CommentDTO dto = new CommentDTO();
		dto.setId(model.getId());
		dto.setVideoId(model.getVideo().getId());
		dto.setUserId(model.getUser().getId());
		dto.setContent(model.getContent());
		dto.setPostDate(model.getPostDate());
		return dto;
	}

	public  List<CommentDTO> toDTOList(List<Comment> models) {
		return models.stream().map(cat -> toDTO(cat)).collect(Collectors.toList());
	}

	public  Comment toModel(CommentDTO dto) {
		Comment model = new Comment();
		model.setId(dto.getId());
		model.setContent(dto.getContent());
		model.setPostDate(dto.getPostDate());
		model.setUser(userService.getUserById(dto.getUserId()));
		model.setVideo(videoService.getVideoById(dto.getVideoId()));
		return model;
	}
}