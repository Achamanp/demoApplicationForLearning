package com.E_CommerceApplication.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.E_CommerceApplication.pagination.UserPageResponse;
import com.E_CommerceApplication.payloads.UserDto;
@Service
@Qualifier
public interface UserService {
        public UserDto createUser(UserDto userDto);
        public UserDto updateUser(UserDto userDto , Integer id );
        public UserDto getUserById(Integer id );
        public UserPageResponse getAllUser(Integer pageNumber, Integer pageSize, String sortBy,String sortDir);
        public void deleteUser(Integer id);
}
