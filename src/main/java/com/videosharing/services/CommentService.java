package com.videosharing.services;

import java.util.List;

import com.videosharing.models.Comment;

public interface CommentService {
    Comment getCommentById(Integer id);
    List<Comment> getAllComments();
    Comment createComment(Comment comment);
    Comment updateComment(Integer id, Comment comment);
    void deleteComment(Integer id);
}
