package com.videosharing.models;

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
@Table(name = "video_tags_mapping")
public class VideoTagsMapping {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "mapping_id")
	private Integer id;

	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "video_id")
	private Video video;

	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "tag_id")
	private VideoTags tag;

}