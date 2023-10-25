package com.videosharing.models;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Data

@Entity
@Table(name = "video_tags")
public class VideoTags {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "tag_id")
	private Integer id;

	@Column(name = "tag_name")
	private String tagName;

	@JsonManagedReference
	@OneToMany(mappedBy = "tag")
	private List<VideoTagsMapping> videoTagsMappings;

}