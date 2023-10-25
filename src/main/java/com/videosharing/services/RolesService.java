package com.videosharing.services;

import java.util.List;

import com.videosharing.models.Roles;

public interface RolesService {
    Roles getRolesById(Integer id);
    List<Roles> getAllRoles();
    Roles createURoles(Roles roles);
    Roles updateRoles(Integer id, Roles roles);
    void deleteRoles(Integer id);
}
