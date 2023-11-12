package com.videosharing.models;

import java.util.Date;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data

@Entity
@Table(name = "video_ratings")
public class VideoRating {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "rating_id")
	private Integer id;

	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "video_id")
	private Video video;

	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	@Column(name = "rating")
	private Integer rating;

	@Column(name = "rated_date")
	@CreationTimestamp
	private Date ratedDate;

}