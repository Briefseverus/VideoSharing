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
	public ResponseEntity<ChannelDTO> getChannelById(@PathVariable Integer id) {
	    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	    CustomUserDetails currentUserDetails = (CustomUserDetails) authentication.getPrincipal();
	    Integer userId = currentUserDetails.getUser().getId();
	    boolean isOwner = channelService.isOwner(id, userId);
	    ChannelDTO channelDTO = channelMapper.toDTO(channelService.getChannelById(id));
	    channelDTO.setOwner(isOwner);
	    return ResponseEntity.ok(channelDTO);
	}

	@GetMapping("/user/{userId}")
	public List<ChannelDTO> getAllChannelsByUserId(@PathVariable Integer userId) {
	    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	    CustomUserDetails currentUserDetails = (CustomUserDetails) authentication.getPrincipal();
	    Integer currentUserId = currentUserDetails.getUser().getId();
	    List<ChannelDTO> channelDTOs = channelMapper.toDTOList(channelService.findByCreatorId(userId));
	    for (ChannelDTO channelDTO : channelDTOs) {
	        channelDTO.setOwner(userId.equals(currentUserId));
	    }
	    return channelDTOs;
	}

	@PostMapping
	public ResponseEntity<String> createChannel(@RequestBody ChannelDTO channelDTO) {
	    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	    CustomUserDetails currentUserDetails = (CustomUserDetails) authentication.getPrincipal();
	    User currentUser = currentUserDetails.getUser();
	    boolean vip = currentUser.isVip();
	    if (!currentUser.isVip() && !vip) {
	        if (currentUser.getChannels().size() >= 2) {
	            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Bạn đã đạt đến giới hạn tạo channel. Hãy mua VIP để tạo nhiều hơn");
	        }
	    }
	    Channel channel = channelMapper.toModel(channelDTO);
	    channel.setCreator(currentUser);
	    channelService.createChannel(channel);

	    return ResponseEntity.ok("Tạo mới channel thành công");
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