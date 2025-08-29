package com.travelmanagementservice.tmss.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.travelmanagementservice.tmss.dto.UserDto;
import com.travelmanagementservice.tmss.entity.User;

@Component
public class UserMapper {
	
	private final ModelMapper modelmapper= new ModelMapper();
	
	
	public UserDto toDto(User user) {
		return modelmapper.map(user,UserDto.class);
	}
	
	public User toEntity(UserDto userDto){
		return modelmapper.map(userDto,User.class);
	}

}
