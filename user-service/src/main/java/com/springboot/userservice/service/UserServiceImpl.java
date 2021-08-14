package com.springboot.userservice.service;

import com.springboot.userservice.shared.userDto;

import java.util.UUID;

public class UserServiceImpl implements UserService {

    @Override
    public userDto createUser(userDto userDetails) {
        userDetails.setUserId(UUID.randomUUID().toString());
        return null;
    }
}
