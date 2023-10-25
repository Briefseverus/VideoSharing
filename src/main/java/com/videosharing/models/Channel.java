
package com.videosharing.models;

import java.util.Date;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(name = "channels")
public class Channel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "channel_id")
	private Integer id;

	@Column(name = "name")
	private String name;

	@Column(name = "description")
	private String description;

	@JsonBackReference("user")
	@ManyToOne
	@JoinColumn(name = "creator_id")
	@JsonIgnore
	private User creator;

	@Column(name = "create_date")
	@CreationTimestamp
	private Date createDate;

	@JsonManagedReference("video")
	@OneToMany(mappedBy = "channel")
	private List<Video> videos;

	@JsonManagedReference("user_channel_sub") 
	@OneToMany(mappedBy = "channel", cascade = CascadeType.ALL)
	private List<UserChannelSub> subscribers;

}