package com.videosharing.services;

import java.util.List;

import com.videosharing.models.UserChannelSub;

public interface UserChannelSubService {
	UserChannelSub getUserChannelSubById(Integer id);

	List<UserChannelSub> getAllUserChannelSubs();

	UserChannelSub createUserChannelSub(UserChannelSub userChannelSub);
	// Các phương thức tương tự cho UserChannelSub

	UserChannelSub updateUserChannelSub(Integer id, UserChannelSub userChannelSub);

	void deleteUserChannelSub(Integer id);
}
