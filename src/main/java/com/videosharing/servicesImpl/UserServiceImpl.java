package com.videosharing.servicesImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.videosharing.models.Roles;
import com.videosharing.models.User;
import com.videosharing.models.UserRoleId;
import com.videosharing.models.UserRoles;
import com.videosharing.repositories.RolesRepository;
import com.videosharing.repositories.UserRepository;
import com.videosharing.services.UserRolesService;
import com.videosharing.services.UserService;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RolesRepository rolesRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private UserRolesService userRolesService;

	@Override
	public User getUserById(Integer id) {
		return userRepository.findById(id).orElse(null);
	}

	@Override
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	@Override
	public User createUser(User user) {

		String username = user.getUsername();

		boolean exists = userRepository.existsByUsername(username);

		if (exists) {
			throw new RuntimeException("Username already exists");
		}

		user.setPassword(passwordEncoder.encode(user.getPassword()));
		user.setRole("USER");
		user.setEnabled(true);

		User savedUser = userRepository.save(user);

		UserRoles userRoles = new UserRoles();
		UserRoleId userRoleId = new UserRoleId();
		userRoleId.setUserId(savedUser.getId());

		Roles role = rolesRepository.findByName("USER");
		userRoleId.setRoleId(role.getId());

		userRoles.setId(userRoleId);
		userRoles.setUser(savedUser);
		userRoles.setRole(role);

		userRolesService.createUserRoles(userRoles);

		return savedUser;

	}

	@Override
	public User updateUser(Integer id, User user) {
	    User existingUser = userRepository.findById(id).orElse(null);

	    if (existingUser != null) {
	        String newUsername = user.getUsername();

	        
	        if (newUsername != null && userRepository.existsByUsernameAndIdNot(newUsername, id)) {
	            throw new RuntimeException("Username already exists");
	        }

	        if (user.getEmail() != null) {
	            existingUser.setEmail(user.getEmail());
	        }

	        if (user.getPassword() != null) {
	            existingUser.setPassword(passwordEncoder.encode(user.getPassword()));
	        }

	        if (newUsername != null) {
	            existingUser.setUsername(newUsername);
	        }

	        return userRepository.save(existingUser);
	    } else {
	        System.out.println("Cannot update user");
	    }

	    return null;
	}


	@Override
	public void deleteUser(Integer id) {
		userRepository.deleteById(id);
	}

	@Override
	public User getFullUserDetails(User user) {
		return user;
	}

	@Override
	public User getSafeUserDetails(User user) {
		User temp = new User();
		temp.setId(user.getId());
		temp.setUsername(user.getUsername());
		return temp;
	}
}
