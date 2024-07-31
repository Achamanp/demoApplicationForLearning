package com.E_CommerceApplication.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.E_CommerceApplication.entity.Role;
//import com.E_CommerceApplication.entity.Role;
import com.E_CommerceApplication.entity.User;
import com.E_CommerceApplication.exception.ResourceNotFoundException;
import com.E_CommerceApplication.pagination.UserPageResponse;
import com.E_CommerceApplication.payloads.UserDto;
import com.E_CommerceApplication.repository.RoleRepository;
//import com.E_CommerceApplication.repository.RoleRepository;
import com.E_CommerceApplication.repository.UserRepository;
import com.E_CommerceApplication.service.UserInfoDetail;
//import com.E_CommerceApplication.service.UserInfoDetail;
import com.E_CommerceApplication.service.UserService;
@Service
@Primary
public class UserServiceImpl implements UserService,UserDetailsService{
	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Override
    public UserDto createUser(UserDto userDto, Integer roleId) {
    	 Role role = this.roleRepository.findById(roleId)
   	            .orElseThrow(() -> new ResourceNotFoundException("Role", "Role ID", roleId));
    	        User user = this.modelMapper.map(userDto, User.class);
    	        user.setPassword(this.passwordEncoder.encode(user.getPassword()));
    	        user.getRoles().add(role);
    	        User savedUser = this.userRepository.save(user);
    	        return this.modelMapper.map(savedUser, UserDto.class);
    }

	@Override
	public UserDto updateUser(UserDto userDto, Integer id) {
		User user = this.userRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("User","User Id", id));
		user.setAddress(userDto.getAddress());
		user.setEmail(userDto.getEmail());
		user.setMobileNumber(userDto.getMobileNumber());
		user.setName(userDto.getName());
		user.setPassword(this.passwordEncoder.encode(userDto.getPassword()));
		User updatedUser = this.userRepository.save(user);
		return this.modelMapper.map(updatedUser, UserDto.class);
	}

	@Override
	public UserDto getUserById(Integer id) {
		User user = this.userRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("User","User id", id));
		return this.modelMapper.map(user, UserDto.class);
	}

	@Override
	public UserPageResponse getAllUser(Integer pageNumber, Integer pageSize, String sortBy, String sortDir) {
		Sort sort = null;
		if(sortDir.equalsIgnoreCase("asc")) {
			sort = Sort.by(sortBy).ascending();
		}
		else sort = Sort.by(sortBy).descending();
		Pageable p = PageRequest.of(pageNumber, pageSize, sort);
		Page<User> page = this.userRepository.findAll(p);
		List<User> users = page.getContent();
		List<UserDto> userDtos = users.stream().map(allusers->this.modelMapper.map(allusers, UserDto.class)).collect(Collectors.toList());
		UserPageResponse response = new UserPageResponse();
		response.setContent(userDtos);
		response.setLastpage(page.isLast());
		response.setPageSize(page.getSize());
		response.setTotalElement(page.getTotalElements());
		response.setPageNumber(page.getNumber());
		response.setTotalPages(page.getTotalPages());
		return response;
	}

	@Override
	public void deleteUser(Integer id) {
		User user = this.userRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("User", "User Id", id));
		this.userRepository.delete(user);
	}
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> user = this.userRepository.findByName(username);
		return user.map(UserInfoDetail::new)
				.orElseThrow(()-> new UsernameNotFoundException("User name not found"+ username));
	}


}
