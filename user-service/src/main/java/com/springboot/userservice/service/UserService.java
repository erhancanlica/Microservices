package com.springboot.userservice.service;

import com.springboot.userservice.shared.userDto;

public interface UserService {
    userDto createUser(userDto userDetails);
}
