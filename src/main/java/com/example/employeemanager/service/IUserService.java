package com.example.employeemanager.service;

import com.example.employeemanager.model.User;

import java.util.List;

public interface IUserService {

    User findByUsername(String username);

    List<String> findRolesByUsername(String username);
}
