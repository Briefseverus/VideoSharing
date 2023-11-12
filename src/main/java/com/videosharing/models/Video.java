package com.videosharing.models;

import java.util.Date;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Data

@Entity
@Table(name = "videos")
public class Video {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "video_id")
	private Integer id;

	@Column(name = "title")
	private String title;

	@Column(name = "description")
	private String description;

	@Column(name = "filename")
	private String filename;

	@Column(name = "upload_date")
	@CreationTimestamp
	private Date uploadDate;

	@Column(name = "duration")
	private long duration;

	@Column(name = "videoUrl")
	private String videoUrl;

	

	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "channel_id")
	private Channel channel;

	@JsonManagedReference
	@OneToMany(mappedBy = "video")
	private List<VideoTagsMapping> tags;

	@JsonManagedReference
	@OneToMany(mappedBy = "video")
	private List<VideoCategories> categories;

	@JsonManagedReference
	@OneToMany(mappedBy = "video", cascade = CascadeType.ALL)
	private List<Comment> comments;

	@JsonManagedReference
	@OneToMany(mappedBy = "video", cascade = CascadeType.ALL)
	private List<VideoView> views;

	@JsonManagedReference
	@OneToMany(mappedBy = "video", cascade = CascadeType.ALL)
	private List<VideoRating> ratings;

}