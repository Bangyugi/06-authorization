package com.bangvan.demologin.controller;

import com.bangvan.demologin.dto.request.ApiResponse;
import com.bangvan.demologin.dto.request.UserCreationRequest;
import com.bangvan.demologin.dto.request.UserUdateRequest;
import com.bangvan.demologin.dto.respond.UserRespond;
import com.bangvan.demologin.entity.User;
import com.bangvan.demologin.service.UserService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
//@RequestMapping("/user")
public class UserController {
    private static final Logger log = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private UserService userService;
    @Autowired
    private ModelMapper modelMapper;

    @PostMapping("/user")
    ApiResponse<UserRespond> createUser(@RequestBody @Valid UserCreationRequest request) {
        ApiResponse<UserRespond> apiResponse = new ApiResponse<>();
        apiResponse.setResult(userService.createUser(request));
        apiResponse.setCode(1000);
        apiResponse.setMessage("Create user success");
        return apiResponse;
    }

    @GetMapping("/user")
    public ApiResponse<List<UserRespond>> getAllUsers() {

        var authentication = SecurityContextHolder.getContext().getAuthentication();

        ApiResponse<List<UserRespond>> apiResponse = new ApiResponse<>();
        apiResponse.setResult(userService.getAllUsers());
        apiResponse.setCode(1000);
        apiResponse.setMessage("Get all users success");
        return apiResponse;
    }

    @GetMapping("/user/{userId}")
    public UserRespond getUserById(@PathVariable Long userId) {
        return modelMapper.map(userService.getUserById(userId),UserRespond.class);

    }

    @PutMapping("/user/{userId}")
    public UserRespond updateUser(@RequestBody UserUdateRequest request, @PathVariable Long userId) {
        return userService.updateUser(request, userId);
    }

    @DeleteMapping("/{userId}")
    public User deleteUser(@PathVariable Long userId) {
        return userService.deleteUser(userId);
    }
}
