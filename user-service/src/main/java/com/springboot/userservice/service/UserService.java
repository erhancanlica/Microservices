package com.springboot.userservice.service;

import com.springboot.userservice.shared.UserDto;

public interface UserService {
    UserDto createUser(UserDto userDetails);
}
