package com.videosharing.services;

import java.util.List;

import com.videosharing.models.Comment;

public interface CommentService {
    Comment getCommentById(Integer id);
  
    Comment createComment(Comment comment);
    Comment updateComment(Integer id, Comment comment);
    void deleteComment(Integer id);
    boolean isOwner(Integer commendId, Integer userId);
	List<Comment> getAllCommentByVideoId(Integer videoId);
	List<Comment> getAllCommentByUserId(Integer videoId);
    
}
