package com.E_CommerceApplication.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.E_CommerceApplication.pagination.UserPageResponse;
import com.E_CommerceApplication.payloads.ApiResponse;
import com.E_CommerceApplication.payloads.UserDto;
import com.E_CommerceApplication.service.UserService;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @Autowired
    private UserService userService;
    @PostMapping("/role/{id}")
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto,@PathVariable Integer id) {
       UserDto users = this.userService.createUser(userDto, id);
      return new ResponseEntity<UserDto>(users,HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable Integer id, @RequestBody UserDto userDto) {
        UserDto updatedUser = userService.updateUser(userDto, id);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Integer id) {
        UserDto userDto = userService.getUserById(id);
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN_USER')")
    public ResponseEntity<UserPageResponse> getAllUsers(
            @RequestParam(defaultValue = "0") Integer pageNumber,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir) {
        UserPageResponse response = userService.getAllUser(pageNumber, pageSize, sortBy, sortDir);
        return new ResponseEntity<UserPageResponse>(response,HttpStatus.OK);
        
    }
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable Integer id) {
        userService.deleteUser(id);
        return new ResponseEntity<ApiResponse>(new ApiResponse("User Deleted Successfully", true),HttpStatus.NO_CONTENT);
    }
}
