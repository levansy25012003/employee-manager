package com.example.employeemanager.repository;

import com.example.employeemanager.model.Department;

import java.util.List;

public interface IDepartmentRepository {
        List<Department> findAll();
}
