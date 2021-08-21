package com.springboot.userservice.controller;

import com.springboot.userservice.service.UserService;
import com.springboot.userservice.shared.UserDto;
import com.springboot.userservice.ui.model.CreateUserRequestModel;
import com.springboot.userservice.ui.model.CreateUserResponseModel;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/users")
public class UserController {

    private final Environment environment;
    private final UserService userService;

    public UserController(Environment environment, UserService userService) {
        this.environment = environment;
        this.userService = userService;
    }

    @GetMapping("/status/check")
    public String status() {
        return "Working on port " + environment.getProperty("local.server.port")
                + ", with token = " + environment.getProperty("token.secret");
    }

    @PostMapping(
            consumes = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE },
            produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE }
    )
    public ResponseEntity<CreateUserResponseModel> createUser(@Valid @RequestBody CreateUserRequestModel userDetails) {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        UserDto userDto = modelMapper.map(userDetails, UserDto.class);
        UserDto createdUser = userService.createUser(userDto);
        CreateUserResponseModel responseModel = modelMapper.map(createdUser, CreateUserResponseModel.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseModel);
    }
}
