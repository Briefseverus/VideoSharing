package com.videosharing.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.videosharing.configs.CustomUserDetails;
import com.videosharing.dtos.UserDTO;
import com.videosharing.dtos.UserUpdateDTO;
import com.videosharing.mappers.UserMapper;
import com.videosharing.mappers.UserUpdateMapper;
import com.videosharing.models.User;
import com.videosharing.services.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping("/{id}")
	public ResponseEntity<UserDTO> getUserById(@PathVariable Integer id, Authentication authentication) {
		CustomUserDetails currentUserDetails = (CustomUserDetails) authentication.getPrincipal();
		User currentUser = currentUserDetails.getUser();
		System.out.println(id);

		System.out.println(currentUser.getId());
		User targetUser = userService.getUserById(id);

		if (currentUser.getId().equals(targetUser.getId())) {
			UserDTO userDTO = UserMapper.toDTO(userService.getFullUserDetails(targetUser));

			return ResponseEntity.ok(userDTO);
		} else {
			UserDTO userDTO = UserMapper.toDTO(userService.getSafeUserDetails(targetUser));
			return ResponseEntity.ok(userDTO);
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<UserDTO> updateUser(@PathVariable Integer id, @RequestBody UserUpdateDTO userUpdateDTO,
			Authentication authentication) {
		System.out.println(id);

		CustomUserDetails currentUserDetails = (CustomUserDetails) authentication.getPrincipal();
		User currentUser = currentUserDetails.getUser();
		System.out.println(currentUser.getId());

		if (currentUser.getId().equals(id)) {
			User user = UserUpdateMapper.toModel(userUpdateDTO);
			User updatedUser = userService.updateUser(id, user);
			UserDTO updatedDTO = UserMapper.toDTO(updatedUser);
			return ResponseEntity.ok(updatedDTO);

		} else {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).build();

		}

	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteUser(@PathVariable Integer id, Authentication authentication) {
		CustomUserDetails currentUserDetails = (CustomUserDetails) authentication.getPrincipal();
		User currentUser = currentUserDetails.getUser();

		if (currentUser.getId().equals(id)) {
			userService.deleteUser(id);
			return ResponseEntity.noContent().build();
		} else {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
		}
	}

}