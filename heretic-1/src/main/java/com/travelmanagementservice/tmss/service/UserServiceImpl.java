package com.travelmanagementservice.tmss.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.travelmanagementservice.tmss.dto.UserDto;
import com.travelmanagementservice.tmss.entity.User;
import com.travelmanagementservice.tmss.exceptions.InvalidRequestException;
import com.travelmanagementservice.tmss.exceptions.ResourceNotFoundException;
import com.travelmanagementservice.tmss.mapper.UserMapper;
import com.travelmanagementservice.tmss.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService{
	
	private final UserRepository repo;
	private final UserMapper mapper;
	private final PasswordEncoder passwordEncoder;
	
	public UserServiceImpl(UserRepository repo, UserMapper mapper, PasswordEncoder passwordEncoder) {
        this.repo = repo;
        this.mapper = mapper;
        this.passwordEncoder = passwordEncoder;
    }
	@Override
	public UserDto registerUser(UserDto dto) {
		if(repo.findByUsername(dto.getUsername()).isPresent()) {
			throw new InvalidRequestException("username '"+dto.getUsername()+"'is alreadytaken");
		}
		
		User user=mapper.toEntity(dto);
		user.setId(null);		
		user.setPassword(passwordEncoder.encode(dto.getPassword()));
		return mapper.toDto(repo.save(user));
		
		
	}
	
	@Override
	public UserDto getUserByUsername(String username) {
		return repo.findByUsername(username)
				.map(mapper::toDto)
				  .orElseThrow(()-> new ResourceNotFoundException("user not found with username: "+username));
	}
	
	
}
