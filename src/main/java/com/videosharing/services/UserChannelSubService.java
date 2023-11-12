package com.videosharing.services;

import java.util.List;

import com.videosharing.models.UserChannelSub;

public interface UserChannelSubService {
	  UserChannelSub getUserChannelSubById(Integer id);

	  List<UserChannelSub> getAllUserChannelSubs();

	  UserChannelSub createUserChannelSub(UserChannelSub userChannelSub);

	  UserChannelSub updateUserChannelSub(Integer id, UserChannelSub userChannelSub);

	  void deleteUserChannelSub(Integer id);

	 
	  boolean isSubscribed(Integer userId, Integer channelId);

	  void subscribeChannel(Integer userId, Integer channelId);

	
	  void unsubscribeChannel(Integer userId, Integer channelId);

	Integer getTotalSubscriber(Integer channelId);
	}