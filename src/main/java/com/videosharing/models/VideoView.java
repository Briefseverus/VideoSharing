package com.videosharing.models;

import java.util.Date;

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
@Table(name = "video_views")
public class VideoView {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "view_id")
	private Integer id;

	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "video_id")
	private Video video;

	@Column(name = "viewer_ip")
	private String viewerIp;

	@Column(name = "view_datetime")
	private Date viewDatetime;

}