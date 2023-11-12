package com.videosharing.servicesImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.videosharing.models.Channel;
import com.videosharing.models.User;
import com.videosharing.models.UserChannelSub;
import com.videosharing.repositories.UserChannelSubRepository;
import com.videosharing.services.ChannelService;
import com.videosharing.services.UserChannelSubService;
import com.videosharing.services.UserService;

@Service
public class UserChannelSubServiceImpl implements UserChannelSubService {

	@Autowired
	private UserChannelSubRepository userChannelSubRepository;

	@Autowired
	UserService userService;

	@Autowired
	ChannelService channelService;

	@Override
	public UserChannelSub getUserChannelSubById(Integer id) {
		return userChannelSubRepository.findById(id).orElse(null);
	}

	@Override
	public List<UserChannelSub> getAllUserChannelSubs() {
		return userChannelSubRepository.findAll();
	}

	@Override
	public UserChannelSub createUserChannelSub(UserChannelSub userChannelSub) {
		return userChannelSubRepository.save(userChannelSub);
	}

	@Override
	public boolean isSubscribed(Integer userId, Integer channelId) {
		return userChannelSubRepository.existsByUserIdAndChannelId(userId, channelId);
	}

	@Override
	public void subscribeChannel(Integer userId, Integer channelId) {
		UserChannelSub userChannelSub = new UserChannelSub();
		userChannelSub.setUser(userService.getUserById(userId));
		userChannelSub.setChannel(channelService.getChannelById(channelId));
		userChannelSubRepository.save(userChannelSub);
	}

	@Override
	public void unsubscribeChannel(Integer userId, Integer channelId) {
		userChannelSubRepository.deleteByUserIdAndChannelId(userId, channelId);
	}

	@Override
	public UserChannelSub updateUserChannelSub(Integer id, UserChannelSub userChannelSub) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteUserChannelSub(Integer id) {
		// TODO Auto-generated method stub

	}


	@Override
	public Integer getTotalSubscriber(Integer channelId) {
		return userChannelSubRepository.countByAndChannelId(channelId);
	}

}