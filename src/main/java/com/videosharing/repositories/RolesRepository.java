package com.videosharing.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.videosharing.models.Roles;

@Repository
public interface RolesRepository extends JpaRepository<Roles, Integer> {

	Roles findByName(String string);
	
}
