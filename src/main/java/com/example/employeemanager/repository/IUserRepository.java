package com.example.employeemanager.repository;

import com.example.employeemanager.model.User;

import java.util.List;

public interface IUserRepository {

    User findByUsername(String username);

    List<String> findRolesByUsername(String username);

}
