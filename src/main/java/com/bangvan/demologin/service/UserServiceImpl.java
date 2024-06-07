package com.bangvan.demologin.service;

import com.bangvan.demologin.dto.request.UserCreationRequest;
import com.bangvan.demologin.dto.request.UserUdateRequest;
import com.bangvan.demologin.dto.respond.UserRespond;
import com.bangvan.demologin.entity.Role;
import com.bangvan.demologin.entity.User;
import com.bangvan.demologin.exception.AppException;
import com.bangvan.demologin.exception.ErrorCode;
import com.bangvan.demologin.repository.RoleRepository;
import com.bangvan.demologin.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public UserRespond createUser(UserCreationRequest request) {
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new AppException(ErrorCode.USERNAME_EXISTED);
        }
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new AppException(ErrorCode.EMAIL_EXISTED);
        }
        User user = modelMapper.map(request, User.class);
        user.setRoles(request.getRoles().stream().map(roleName ->
        {
            Role role = roleRepository.findByRole(roleName);
            if (role == null) {
                throw new AppException(ErrorCode.ROLE_NOT_EXISTED);
            }
            return role;
        }).collect(Collectors.toSet()));
        userRepository.save(user);
        return modelMapper.map(user, UserRespond.class);
    }

    @Override
    public List<UserRespond> getAllUsers() {
        return userRepository.findAll().stream().map(user -> modelMapper.map(user,UserRespond.class)).collect(Collectors.toList());
    }

    @Override
    public UserRespond getUserById(Long id) {
        return modelMapper.map( userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found")), UserRespond.class);
    }

    @Override
    public UserRespond updateUser(UserUdateRequest request, Long id) {
        User user = userRepository.findById(id).orElse(null);

        if (user != null) {
            modelMapper.map(request, user);
            user.setRoles(request.getRoles().stream().map(roleName ->
            {
                Role role = roleRepository.findByRole(roleName);
                if (role == null) {
                    throw new AppException(ErrorCode.ROLE_NOT_EXISTED);
                }
                return role;
            }).collect(Collectors.toSet()));
            userRepository.save(user);
            return modelMapper.map(user, UserRespond.class);
        }
        else
        {
            return null;
        }
    }

    @Override
    public User deleteUser(Long id) {
        User user = userRepository.findById(id).orElse(null);
        if (user != null) {
            userRepository.delete(user);
            return user;
        }
        else
            return null;
    }
}
