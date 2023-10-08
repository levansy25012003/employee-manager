package com.example.employeemanager.service.impl;

import com.example.employeemanager.model.Department;
import com.example.employeemanager.repository.IDepartmentRepository;
import com.example.employeemanager.repository.impl.DepartmentRepository;
import com.example.employeemanager.service.IDepartmentService;

import java.util.List;

public class DepartmentService implements IDepartmentService {

    IDepartmentRepository departmentRepository = new DepartmentRepository();

    @Override
    public List<Department> findAll() {
        return departmentRepository.findAll();
    }
}
