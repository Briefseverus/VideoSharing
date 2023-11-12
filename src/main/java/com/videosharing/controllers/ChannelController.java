package com.videosharing.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
	
	@Autowired
	private ChannelMapper channelMapper;
	

	@GetMapping("/{id}")
	public ChannelDTO getChannelById(@PathVariable Integer id) {
		//Chưa implememnt logic kiểm tra owner;
		//Dự định sẽ implement và trả về cho FE một biến boolean isOwner
		return channelMapper.toDTO(channelService.getChannelById(id));
	}

	@GetMapping("/{userId}")
	public List<ChannelDTO> getAllChannelsByUserId(@PathVariable Integer userId) {
		return channelMapper.toDTOList(channelService.findByCreatorId(userId));
	}

	@PostMapping
	public ChannelDTO createChannel(@RequestBody ChannelDTO channelDTO) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		CustomUserDetails currentUserDetails = (CustomUserDetails) authentication.getPrincipal();
		Channel channel = channelMapper.toModel(channelDTO);
		User currentUser = currentUserDetails.getUser();
		channel.setCreator(currentUser);
		return channelMapper.toDTO(channelService.createChannel(channel));
	}

	@PutMapping("/{id}")
	public ResponseEntity<ChannelDTO> updateChannel(@PathVariable Integer id, @RequestBody ChannelDTO channelDTO) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	    CustomUserDetails currentUserDetails = (CustomUserDetails) authentication.getPrincipal();
	    Integer userId = currentUserDetails.getUser().getId();
	    if (!channelService.isOwner(id, userId)) {
	        return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
	    }
	    Channel updatedChannel = channelService.updateChannel(id, channelMapper.toModel(channelDTO));
	    ChannelDTO updatedDTO = channelMapper.toDTO(updatedChannel);
	    return ResponseEntity.ok(updatedDTO);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteChannel(@PathVariable Integer id) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	    CustomUserDetails currentUserDetails = (CustomUserDetails) authentication.getPrincipal();
	    Integer userId = currentUserDetails.getUser().getId();
	    if (!channelService.isOwner(id, userId)) {
	        return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
	    }
	    channelService.deleteChannel(id);
	    return ResponseEntity.noContent().build();
	}
}