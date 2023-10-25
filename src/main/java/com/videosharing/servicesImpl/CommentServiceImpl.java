package com.videosharing.servicesImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.videosharing.models.Comment;
import com.videosharing.repositories.CommentRepository;
import com.videosharing.services.CommentService;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentRepository commentRepository;

    @Override
    public Comment getCommentById(Integer id) {
        return commentRepository.findById(id).orElse(null);
    }

    @Override
    public List<Comment> getAllComments() {
        return commentRepository.findAll();
    }

    @Override
    public Comment createComment(Comment comment) {
        return commentRepository.save(comment);
    }

    @Override
    public Comment updateComment(Integer id, Comment comment) {
        Comment existingComment = commentRepository.findById(id).orElse(null);
        if (existingComment != null) {
            // Update comment properties here
            existingComment.setContent(comment.getContent());
            // ...
            return commentRepository.save(existingComment);
        }
        return null;
    }

    @Override
    public void deleteComment(Integer id) {
        commentRepository.deleteById(id);
    }
}
