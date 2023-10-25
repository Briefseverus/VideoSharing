package com.videosharing.services;

import java.util.List;

import com.videosharing.models.User;

public interface UserService {
    User getUserById(Integer id);
    List<User> getAllUsers();
    User createUser(User user);
    User updateUser(Integer id, User user);
    void deleteUser(Integer id);
	User getFullUserDetails(User targetUser);
	User getSafeUserDetails(User targetUser);
}
