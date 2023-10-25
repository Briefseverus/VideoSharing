package com.videosharing.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.videosharing.dtos.UserChannelSubDTO;
import com.videosharing.mappers.UserChannelSubMapper;
import com.videosharing.services.UserChannelSubService;

@RestController
@RequestMapping("/api/user-channel-subs")
public class UserChannelSubController {

	@Autowired
	private UserChannelSubService userChannelSubService;

	@GetMapping("/{id}")
	public UserChannelSubDTO getUserChannelSubById(@PathVariable Integer id) {
		return UserChannelSubMapper.toDTO(userChannelSubService.getUserChannelSubById(id));
	}

	@GetMapping
	public List<UserChannelSubDTO> getAllUserChannelSubs() {
		return UserChannelSubMapper.toDTOList(userChannelSubService.getAllUserChannelSubs());
	}

	@PostMapping
	public UserChannelSubDTO createUserChannelSub(@RequestBody UserChannelSubDTO userChannelSubDTO) {
		return UserChannelSubMapper
				.toDTO(userChannelSubService.createUserChannelSub(UserChannelSubMapper.toModel(userChannelSubDTO)));
	}

	@PutMapping("/{id}")
	public UserChannelSubDTO updateUserChannelSub(@PathVariable Integer id,
			@RequestBody UserChannelSubDTO userChannelSubDTO) {
		return UserChannelSubMapper.toDTO(
				userChannelSubService.updateUserChannelSub(id, UserChannelSubMapper.toModel(userChannelSubDTO)));
	}

	@DeleteMapping("/{id}")
	public void deleteUserChannelSub(@PathVariable Integer id) {
		userChannelSubService.deleteUserChannelSub(id);
	}

}