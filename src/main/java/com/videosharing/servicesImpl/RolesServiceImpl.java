package com.videosharing.servicesImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.videosharing.models.Roles;
import com.videosharing.repositories.RolesRepository;
import com.videosharing.services.RolesService;

@Service
public class RolesServiceImpl implements RolesService {
	@Autowired
	private RolesRepository RolesRepository;

	@Override
	public Roles getRolesById(Integer id) {
		return RolesRepository.findById(id).orElse(null);
	}

	@Override
	public List<Roles> getAllRoles() {
		return RolesRepository.findAll();
	}

	@Override
	public Roles updateRoles(Integer id, Roles Roles) {
		Roles existingRoles = RolesRepository.findById(id).orElse(null);
		if (existingRoles != null) {
			// Update Roles properties here

			return RolesRepository.save(existingRoles);
		}
		return null;
	}

	@Override
	public Roles createURoles(Roles roles) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteRoles(Integer id) {
		// TODO Auto-generated method stub
		
	}



}
