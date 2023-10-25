package com.videosharing.servicesImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.videosharing.models.UserRoles;
import com.videosharing.repositories.UserRolesRepository;
import com.videosharing.services.UserRolesService;

@Service
public class UserRolesServiceImpl implements UserRolesService {
	@Autowired
	private UserRolesRepository UserRolesRepository;

	@Override
	public UserRoles getUserRolesById(Integer id) {
		return UserRolesRepository.findById(id).orElse(null);
	}

	@Override
	public List<UserRoles> getAllUserRoles() {
		return UserRolesRepository.findAll();
	}

	@Override
	public UserRoles updateUserRoles(Integer id, UserRoles UserRoles) {
		UserRoles existingUserRoles = UserRolesRepository.findById(id).orElse(null);
		if (existingUserRoles != null) {
			// Update UserRoles properties here

			return UserRolesRepository.save(existingUserRoles);
		}
		return null;
	}

	@Override  
	public UserRoles createUserRoles(UserRoles userRoles) {
	  return UserRolesRepository.save(userRoles);
	}

	@Override
	public void deleteUserRoles(Integer id) {
		// TODO Auto-generated method stub

	}


}
