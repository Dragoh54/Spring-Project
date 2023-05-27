package com.example.demo.mapper;

import com.example.demo.dto.UserDto;
import com.example.demo.entity.User;

public class UserMapper {
    public static UserDto map(User user) {
        return new UserDto(user.getUserId(), user.getEmail(), user.getRole(),user.getUsername(),user.getGraves());
    }
}
