package com.E_CommerceApplication.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.E_CommerceApplication.entity.Role;
import com.E_CommerceApplication.entity.User;
import com.E_CommerceApplication.exception.ResourceNotFoundException;
import com.E_CommerceApplication.repository.RoleRepository;
import com.E_CommerceApplication.repository.UserRepository;
import com.E_CommerceApplication.service.RoleService;
@Service
public class RoleImpl implements RoleService{
	@Autowired
    private  UserRepository userRepository;
	@Autowired
    private  RoleRepository roleRepository;
	@Override
	 public Role createRole(Role role, Integer id) {
        User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User", "User ID", id));
        Role existingRole = roleRepository.findByName(role.getName());
        if (existingRole == null) {
            existingRole = roleRepository.save(role);
        }
        user.getRoles().add(existingRole);
        userRepository.save(user);
        return existingRole;
    }

	@Override
	public Role getRole(Integer id) {
		Role role = this.roleRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Role", "role id", id));
		return role;
	}

	@Override
	public void deleteRole(Integer id) {
		Role role = this.roleRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Role", "role", id));
		this.roleRepository.delete(role);
	}

	@Override
	public Role updateRole(Integer id, Role roles) {
		Role role = this.roleRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Role", "Role id", id));
		role.setName(roles.getName());
		 Role updatedRole = this.roleRepository.save(role);
		 return updatedRole;
	}

	@Override
	public List<Role> getAllRole(Integer id) {
		List<Role> roles = this.roleRepository.findAll();
		return roles;
	}

}
