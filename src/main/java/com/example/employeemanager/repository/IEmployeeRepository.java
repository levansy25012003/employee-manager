package com.example.employeemanager.repository;

import com.example.employeemanager.dto.EmployeeDTO;
import com.example.employeemanager.dto.EmployeeSearchDTO;
import com.example.employeemanager.model.Employee;

import java.util.List;

public interface IEmployeeRepository {

    List<EmployeeDTO> search(EmployeeSearchDTO employeeSearchDTO);
    Employee findById(int id);

    Employee findByPhoneNumber(String phoneNumber);
    void create(Employee employee);

    void update(Employee employee);

    void delete(int id);

}
