package com.videosharing.servicesImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.videosharing.models.UserChannelSub;
import com.videosharing.repositories.UserChannelSubRepository;
import com.videosharing.services.UserChannelSubService;

@Service
public class UserChannelSubServiceImpl implements UserChannelSubService {
	@Autowired
	private UserChannelSubRepository userChannelSubRepository;

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
	public UserChannelSub updateUserChannelSub(Integer id, UserChannelSub userChannelSub) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteUserChannelSub(Integer id) {
		// TODO Auto-generated method stub
		
	}

	// Cài đặt các phương thức tương tự cho UserChannelSub
}
