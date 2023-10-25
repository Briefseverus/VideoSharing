package com.videosharing.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.videosharing.dtos.CommentDTO;
import com.videosharing.mappers.CommentMapper;
import com.videosharing.services.CommentService;

@RestController
@RequestMapping("/api/comments")
public class CommentController {

	@Autowired
	private CommentService commentService;

	@GetMapping("/{id}")
	public CommentDTO getCommentById(@PathVariable Integer id) {
		return CommentMapper.toDTO(commentService.getCommentById(id));
	}

	@GetMapping
	public List<CommentDTO> getAllComments() {
		return CommentMapper.toDTOList(commentService.getAllComments());
	}

	@PostMapping
	public CommentDTO createComment(@RequestBody CommentDTO commentDTO) {
		return CommentMapper.toDTO(commentService.createComment(CommentMapper.toModel(commentDTO)));
	}

	@PutMapping("/{id}")
	public CommentDTO updateComment(@PathVariable Integer id, @RequestBody CommentDTO commentDTO) {
		return CommentMapper.toDTO(commentService.updateComment(id, CommentMapper.toModel(commentDTO)));
	}

	@DeleteMapping("/{id}")
	public void deleteComment(@PathVariable Integer id) {
		commentService.deleteComment(id);
	}

}