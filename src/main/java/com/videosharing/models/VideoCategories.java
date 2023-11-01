package com.videosharing.models;

import java.util.List;

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
@Table(name = "video_categories")
public class VideoCategories {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "category_mapping_id")
	private Integer id;

	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "video_id")
	private Video video;

	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "category_id")
	private Categories categories;

	public List<Video> getVideos() {
	    return List.of(video);
	}

}