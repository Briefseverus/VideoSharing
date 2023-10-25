package com.videosharing.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.videosharing.configs.CustomUserDetails;
import com.videosharing.dtos.ChannelDTO;
import com.videosharing.mappers.ChannelMapper;
import com.videosharing.models.Channel;
import com.videosharing.models.User;
import com.videosharing.services.ChannelService;

@RestController
@RequestMapping("/api/channels")
public class ChannelController {

	@Autowired
	private ChannelService channelService;

	@GetMapping("/{id}")
	public ChannelDTO getChannelById(@PathVariable Integer id) {
		return ChannelMapper.toDTO(channelService.getChannelById(id));
	}

	@PostMapping
	public ChannelDTO createChannel(@RequestBody ChannelDTO channelDTO, Authentication authentication) {
		CustomUserDetails currentUserDetails = (CustomUserDetails) authentication.getPrincipal();
		Channel channel = ChannelMapper.toModel(channelDTO);
		User currentUser = currentUserDetails.getUser();
		channel.setCreator(currentUser);
		return ChannelMapper.toDTO(channelService.createChannel(channel));
	}

	@PutMapping("/{id}")
	public ResponseEntity<ChannelDTO> updateChannel(@PathVariable Integer id, @RequestBody ChannelDTO channelDTO,
			Authentication authentication) {

		CustomUserDetails currentUserDetails = (CustomUserDetails) authentication.getPrincipal();
		User currentUser = currentUserDetails.getUser();
		Channel existingChannel = channelService.getChannelById(id);

		if (existingChannel.getCreator().getId().equals(currentUser.getId())) {
			Channel updatedChannel = channelService.updateChannel(id, ChannelMapper.toModel(channelDTO));
			ChannelDTO updatedDTO = ChannelMapper.toDTO(updatedChannel);
			return ResponseEntity.ok(updatedDTO);

		} else {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
		}

	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteChannel(@PathVariable Integer id, Authentication authentication) {

		CustomUserDetails currentUserDetails = (CustomUserDetails) authentication.getPrincipal();
		User currentUser = currentUserDetails.getUser();

		Channel existingChannel = channelService.getChannelById(id);

		if (existingChannel.getCreator().getId().equals(currentUser.getId())) {
			channelService.deleteChannel(id);
			return ResponseEntity.noContent().build();

		} else {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
		}
	}
}