package com.travelmanagementservice.tmss.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.travelmanagementservice.tmss.dto.UserDto;
import com.travelmanagementservice.tmss.entity.ApiResponse;
import com.travelmanagementservice.tmss.security.JwtUtil;
import com.travelmanagementservice.tmss.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired private UserService service;
	@Autowired private AuthenticationManager authenticationManager;
	@Autowired private JwtUtil jwtUtil;
	
	
	@PostMapping("/register")
	public ResponseEntity<ApiResponse<UserDto>> addUser(@RequestBody UserDto user){
		UserDto createdUser=service.registerUser(user);
		ApiResponse<UserDto> response=new ApiResponse<>(true,"user registered successfully",createdUser);
		return ResponseEntity.ok(response);
	}
	
	@PostMapping("/login")
	public ResponseEntity<ApiResponse<String>> login(@RequestBody UserDto dto){
		try {
			if(dto.getUsername()==null || dto.getPassword()==null) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST)
						.body(new ApiResponse<>(false,"username and password are required",null));
			}
			
			System.out.println("Attempting login for user: " + dto.getUsername());
		
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(dto.getUsername(),dto.getPassword()));
		
			System.out.println("Authentication successful for user: " + dto.getUsername());
			
			
		    String token=jwtUtil.generateToken(dto.getUsername());
		    ApiResponse<String> response=new ApiResponse<>(true,"login successful",token);
		    return ResponseEntity.ok(response);
		
		}
		catch (AuthenticationException ex) {
	        System.out.println("Authentication failed for user: " + dto.getUsername());
	        System.out.println("Error: " + ex.getMessage());
	        ex.printStackTrace();
	        
	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
	            .body(new ApiResponse<>(false, "Invalid credentials", null));
		}
		catch(Exception e) {
	        System.out.println("Error: " + e.getMessage());
	        e.printStackTrace();
			ApiResponse<String> response=new ApiResponse<>(false,"Login failed",null);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
	}
	
	
	@GetMapping("/me")
	public ResponseEntity<ApiResponse<UserDto>> getCurrentUser(@RequestParam String username){
		UserDto dto=service.getUserByUsername(username);
		ApiResponse<UserDto> response=new ApiResponse<>(true,"userdetails fetched",dto);
		return ResponseEntity.ok(response);
	}
	
}
	
