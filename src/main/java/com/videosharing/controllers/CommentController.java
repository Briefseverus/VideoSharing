package com.videosharing.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.videosharing.configs.CustomUserDetails;
import com.videosharing.dtos.CommentDTO;
import com.videosharing.dtos.UserDTO;
import com.videosharing.mappers.CommentMapper;
import com.videosharing.mappers.UserMapper;
import com.videosharing.models.Comment;
import com.videosharing.models.User;
import com.videosharing.services.CommentService;
import com.videosharing.services.UserService;

@RestController
@RequestMapping("/api/comments")
public class CommentController {

	@Autowired
	private CommentService commentService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private CommentMapper commentMapper;

	@GetMapping("/{id}")
	public CommentDTO getCommentById(@PathVariable Integer id) {
		return commentMapper.toDTO(commentService.getCommentById(id));
	}

	@GetMapping("/{videoId}")
	public List<CommentDTO> getAllCommentsFromVideo(@PathVariable Integer videoId) {
		return commentMapper.toDTOList(commentService.getAllCommentByVideoId(videoId));
	}

	@PostMapping
	public CommentDTO createComment(@RequestBody CommentDTO commentDTO) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		CustomUserDetails  currentUserDetails= (CustomUserDetails) authentication.getPrincipal();
	    UserDTO user = userMapper.toDTO(userService.getUserById(currentUserDetails.getUser().getId())) ;
	    commentDTO.setUserId(user.getId());
		return commentMapper.toDTO(commentService.createComment(commentMapper.toModel(commentDTO)));
	}

	@PutMapping("/{id}")
	public ResponseEntity<CommentDTO> updateComment(@PathVariable Integer id, @RequestBody CommentDTO commentDTO) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		CustomUserDetails currentUserDetails = (CustomUserDetails) authentication.getPrincipal();
	    Integer currentUserId = currentUserDetails.getUser().getId();

	    if (commentService.isOwner(id, currentUserId)) {
	        Comment updatedComment = commentService.updateComment(id, commentMapper.toModel(commentDTO));
	        return ResponseEntity.ok(commentMapper.toDTO(updatedComment));
	    } else {
	        return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
	    }
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteComment(@PathVariable Integer id) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		CustomUserDetails currentUserDetails = (CustomUserDetails) authentication.getPrincipal();
	    Integer currentUserId = currentUserDetails.getUser().getId();

	    if (commentService.isOwner(id, currentUserId)) {
	        commentService.deleteComment(id);
	        return ResponseEntity.noContent().build();
	    } else {
	        return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
	    }
	}


}