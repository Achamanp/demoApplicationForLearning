package com.E_CommerceApplication.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.E_CommerceApplication.entity.Role;
@Service
public interface RoleService {
        public Role createRole(Role role, Integer id);
        public Role getRole(Integer id);
        public void deleteRole(Integer id);
        public Role updateRole(Integer id, Role roles);
        public List<Role> getAllRole(Integer id);
}
