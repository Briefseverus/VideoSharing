package com.videosharing.services;

import java.util.List;

import com.videosharing.models.UserRoles;

public interface UserRolesService {
    UserRoles getUserRolesById(Integer id);
    List<UserRoles> getAllUserRoles();
    UserRoles updateUserRoles(Integer id, UserRoles userRoles);
    void deleteUserRoles(Integer id);
	UserRoles createUserRoles(UserRoles userRoles);
}
