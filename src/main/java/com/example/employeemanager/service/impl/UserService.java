package com.example.employeemanager.service.impl;

import com.example.employeemanager.model.User;
import com.example.employeemanager.repository.IUserRepository;
import com.example.employeemanager.repository.impl.UserRepository;
import com.example.employeemanager.service.IUserService;

import java.util.List;

public class UserService implements IUserService {
    IUserRepository userRepository = new UserRepository();

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);

    }

    @Override
    public List<String> findRolesByUsername(String username) {
        return userRepository.findRolesByUsername(username);
    }
}
