package com.videosharing.models;

import java.util.Date;
import java.util.List;
import java.util.Set;


import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
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
@Table(name = "users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private Integer id;

	@Column(name = "username")
	private String username;

	@Column(name = "password")
	private String password;

	@Column(name = "email")
	private String email;

	@Column(name = "enabled")
	private boolean enabled;

	@Column(name = "join_date")
	@CreationTimestamp
	private Date joinDate;

	@Column(name = "role")
	private String role;

	@JsonManagedReference
	@OneToMany(mappedBy = "creator", cascade = CascadeType.ALL)
	private List<Channel> channels;

	
	@JsonManagedReference
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private List<UserChannelSub> subscriptions;

	@JsonManagedReference
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private List<Comment> comments;

	@JsonManagedReference
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private List<VideoRating> videoRatings;

	@OneToMany(mappedBy = "user")
	private Set<UserRoles> userRoles;



}