package com.videosharing.models;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "user_roles")
public class UserRoles {

	@EmbeddedId
	private UserRoleId id;

	@MapsId("userId")
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	@MapsId("roleId")
	@ManyToOne
	@JoinColumn(name = "role_id")
	private Roles role;

}
