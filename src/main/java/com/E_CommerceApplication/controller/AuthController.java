//package com.E_CommerceApplication.controller;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.E_CommerceApplication.entity.AuthRequest;
//import com.E_CommerceApplication.service.JwtService;
//
//@RestController
//public class AuthController {
//	@Autowired
//	private JwtService jwtService;
//	@Autowired
//	private AuthenticationManager authenticationManager;
//	
//	@PostMapping("/login")
//    public String addUser(@RequestBody AuthRequest authRequest){
//        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
//        if(authenticate.isAuthenticated()){
//            return jwtService.generateToken(authRequest.getUsername());
//        }else {
//            throw new UsernameNotFoundException("Invalid user request");
//        }
//    }
//
//}
