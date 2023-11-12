package com.videosharing.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.videosharing.models.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {
	List<Comment> findAllByVideoId(Integer videoId);
	List<Comment> findAllByUserId(Integer videoId);
}
