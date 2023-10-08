package com.example.employeemanager.service;

import com.example.employeemanager.dto.EmployeeDTO;
import com.example.employeemanager.dto.EmployeeSearchDTO;
import com.example.employeemanager.model.Employee;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

public interface IEmployeeService {

    List<EmployeeDTO> search(EmployeeSearchDTO employeeSearchDTO);

    Employee findById(int id);

    void create(Employee employee);

    void update(Employee employee);

    void delete(int id);

    void validate(HttpServletRequest request, Map<String, String> errorMessage);

    void validateNumberPhoneExits(HttpServletRequest request, Map<String, String> errorMessage);
}
