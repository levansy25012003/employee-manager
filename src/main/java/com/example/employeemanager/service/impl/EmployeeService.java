package com.example.employeemanager.service.impl;

import com.example.employeemanager.dto.EmployeeDTO;
import com.example.employeemanager.dto.EmployeeSearchDTO;
import com.example.employeemanager.model.Employee;
import com.example.employeemanager.repository.IEmployeeRepository;
import com.example.employeemanager.repository.impl.EmployeeRepository;
import com.example.employeemanager.service.IEmployeeService;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class EmployeeService implements IEmployeeService {
    IEmployeeRepository employeeRepository = new EmployeeRepository();

    @Override
    public List<EmployeeDTO> search(EmployeeSearchDTO employeeSearchDTO) {

        if (employeeSearchDTO.getName() == null) {
            employeeSearchDTO.setName("");
        }
        if (employeeSearchDTO.getPhoneNumber() == null) {
            employeeSearchDTO.setPhoneNumber("");
        }
        if ("".equals(employeeSearchDTO.getFromBirthDate())) {
            employeeSearchDTO.setFromBirthDate(null);
        }
        if ("".equals(employeeSearchDTO.getToBirthDate())) {
            employeeSearchDTO.setToBirthDate(null);
        }

        return employeeRepository.search(employeeSearchDTO);
    }


    @Override
    public Employee findById(int id) {
        return employeeRepository.findById(id);
    }

    @Override
    public void create(Employee employee) {
        employeeRepository.create(employee);
    }

    @Override
    public void update(Employee employee) {
        employeeRepository.update(employee);
    }

    @Override
    public void delete(int id) {
        employeeRepository.delete(id);
    }

    @Override
    public void validate(HttpServletRequest request, Map<String, String> errorMessage) {
        String nameStr = request.getParameter("name").trim();
        String birthDateStr = request.getParameter("birthDate");
        String genderStr = request.getParameter("gender");
        String salaryStr = request.getParameter("salary");
        String phoneNumberStr = request.getParameter("phoneNumber");
        String departmentIdStr = request.getParameter("departmentId");

        if (nameStr.equals("")) {
            errorMessage.put("name", "Tên không được để trống");
        } else if (!nameStr.matches("^[a-zA-ZÀ-ỹ\\s]+$")) {
            errorMessage.put("name", "Tên không được bắt đầu bằng số");
        }

        if (birthDateStr.trim().equals("")) {
            errorMessage.put("birthDate", "Ngày sinh không được để trống");
        } else {
            LocalDate birthDate = LocalDate.parse(birthDateStr);
            LocalDate currentDateBefore15Year = LocalDate.now().plusYears(-15); // 15 năm trước vào ngày hôm nay.

            if (birthDate.isAfter(currentDateBefore15Year)) {
                errorMessage.put("birthDate", "Bắt buộc trên 15 tuổi");
            }
        }

        if (salaryStr.trim().equals("")) {
            errorMessage.put("salary", "Lương không được để trống");
        } else {
            double salary;
            try {
                salary = Double.parseDouble(salaryStr);
                if (salary <= 0) {
                    errorMessage.put("salary", "Lương phải là số dương.");
                }
            } catch (NumberFormatException e) {
                errorMessage.put("salary", "Lương không đúng định dạng.");
            }
        }

        if (phoneNumberStr.trim().equals("")) {
            errorMessage.put("phoneNumber", "Số điện thoại không được để trống.");
        }

        if (departmentIdStr.equals("")) {
            errorMessage.put("departmentId", "Bộ phận không được để trống.");
        }

    }

    @Override
    public void validateNumberPhoneExits(HttpServletRequest request, Map<String, String> errorMessage) {
        String phoneNuberStr = request.getParameter("phoneNumber");
        if (employeeRepository.findByPhoneNumber(phoneNuberStr) != null) {
            errorMessage.put("phoneNumber", "Số điện thoại đã tồn tại.");
        }
    }


}
