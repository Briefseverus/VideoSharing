package com.videosharing.servicesImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.videosharing.models.Channel;
import com.videosharing.repositories.ChannelRepository;
import com.videosharing.services.ChannelService;

@Service
public class ChannelServiceImpl implements ChannelService {
	@Autowired
	private ChannelRepository channelRepository;

	@Override
	public Channel getChannelById(Integer id) {
		return channelRepository.findById(id).orElse(null);
	}

	@Override
	public List<Channel> getAllChannels() {
		return channelRepository.findAll();
	}

	@Override
	public Channel createChannel(Channel channel) {
		return channelRepository.save(channel);
	}

	@Override
	public Channel updateChannel(Integer id, Channel channel) {
		Channel existingChannel = channelRepository.findById(id).orElse(null);
		if (existingChannel == null) return null;
			// Update channel properties here
		existingChannel.setName(channel.getName());
		existingChannel.setDescription(channel.getDescription());
		// ...
		return channelRepository.save(existingChannel);
	}

	@Override
	public void deleteChannel(Integer id) {
		channelRepository.deleteById(id);
	}

	@Override
	public List<Channel> findByCreatorId(Integer id) {
		 return channelRepository.findByCreatorId(id);
	}
	@Override
	public boolean isOwner(Integer channelId, Integer userId) {
	    Channel channel = getChannelById(channelId);
	    return channel != null && channel.getCreator().getId().equals(userId);
	}


}
