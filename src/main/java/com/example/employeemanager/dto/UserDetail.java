package com.example.employeemanager.dto;

import java.util.List;

public class UserDetail {
    private String username;
    private List<String> roles;

    public UserDetail() {
    }

    public UserDetail(String username, List<String> roles) {
        this.username = username;
        this.roles = roles;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }
}
