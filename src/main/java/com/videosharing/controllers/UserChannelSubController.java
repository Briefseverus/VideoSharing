package com.videosharing.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.videosharing.configs.CustomUserDetails;
import com.videosharing.mappers.UserChannelSubMapper;
import com.videosharing.services.UserChannelSubService;

@RestController
@RequestMapping("/api/user-channel-subs")
public class UserChannelSubController {

	@Autowired
	UserChannelSubMapper userChannelSubMapper;

	@Autowired
	private UserChannelSubService userChannelSubService;


   
    @GetMapping("/is-subscribed/{channelId}")
    public boolean isSubscribed(@PathVariable Integer channelId) {
    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    	CustomUserDetails currentUserDetails = (CustomUserDetails) authentication.getPrincipal();
    	int userId = currentUserDetails.getUser().getId();
        return userChannelSubService.isSubscribed(userId, channelId);
    }

  
    @PostMapping("/subscribe/{channelId}")
    public void subscribeChannel(@PathVariable Integer channelId) {
    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    	CustomUserDetails currentUserDetails = (CustomUserDetails) authentication.getPrincipal();
    	int userId = currentUserDetails.getUser().getId();  userChannelSubService.subscribeChannel(userId, channelId);
    }

    @DeleteMapping("/unsubscribe/{channelId}")
    public void unsubscribeChannel(@PathVariable Integer channelId) {
    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    	CustomUserDetails currentUserDetails = (CustomUserDetails) authentication.getPrincipal();
    	int userId = currentUserDetails.getUser().getId();  userChannelSubService.unsubscribeChannel(userId, channelId);
    }
 
    @GetMapping("/total-subscriber/{channelId}")
    public Integer getTotalSubbyChannelId(@PathVariable Integer channelId) {
        return userChannelSubService.getTotalSubscriber(channelId);
    }

}