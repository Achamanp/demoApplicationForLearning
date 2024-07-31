package com.E_CommerceApplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.E_CommerceApplication.entity.Role;
@Repository
public interface RoleRepository extends JpaRepository<Role, Integer>{

	Role findByName(String name);

}
