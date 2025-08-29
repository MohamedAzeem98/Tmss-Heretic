package com.travelmanagementservice.tmss.service;

import com.travelmanagementservice.tmss.dto.UserDto;

public interface UserService {
	UserDto registerUser(UserDto dto);
	UserDto getUserByUsername(String username);

}
