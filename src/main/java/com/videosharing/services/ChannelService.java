package com.videosharing.services;

import java.util.List;

import com.videosharing.models.Channel;

public interface ChannelService {
	Channel getChannelById(Integer id);

	List<Channel> getAllChannels();

	Channel createChannel(Channel channel);

	Channel updateChannel(Integer id, Channel channel);

	void deleteChannel(Integer id);

	List<Channel> findByCreatorId(Integer id);
	
	public boolean isOwner(Integer channelId, Integer userId);

	
}
