package com.videosharing.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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

	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	UserUpdateMapper userUpdateMapper;
	
	@GetMapping("/{id}")
	public ResponseEntity<UserDTO> getUserById(@PathVariable Integer id) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		CustomUserDetails currentUserDetails = (CustomUserDetails) authentication.getPrincipal();
		User currentUser = currentUserDetails.getUser();

		System.out.println(id);

		System.out.println(currentUser.getId());
		User targetUser = userService.getUserById(id);

		if (userService.isOwner(targetUser.getId(), currentUser.getId())) {
			UserDTO userDTO = userMapper.toDTO(userService.getFullUserDetails(targetUser));
			userDTO.setOwner(true);
			return ResponseEntity.ok(userDTO);
		} else {
			UserDTO userDTO = userMapper.toDTO(userService.getSafeUserDetails(targetUser));
			userDTO.setOwner(false);
			return ResponseEntity.ok(userDTO);
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<UserDTO> updateUser(@PathVariable Integer id, @RequestBody UserUpdateDTO userUpdateDTO) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		CustomUserDetails currentUserDetails = (CustomUserDetails) authentication.getPrincipal();
		Integer userId = currentUserDetails.getUser().getId();

		if (!userService.isOwner(userId, id)) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED ).build();
		}
		User user = userUpdateMapper.toModel(userUpdateDTO);
		User updatedUser = userService.updateUser(id, user);
		UserDTO updatedDTO = userMapper.toDTO(updatedUser);
		return ResponseEntity.ok(updatedDTO);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteUser(@PathVariable Integer id) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		CustomUserDetails currentUserDetails = (CustomUserDetails) authentication.getPrincipal();
		Integer userId = currentUserDetails.getUser().getId();

		if (!userService.isOwner(userId, id)) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED ).build();
		}

		userService.deleteUser(id);
		return ResponseEntity.noContent().build();
	}

}