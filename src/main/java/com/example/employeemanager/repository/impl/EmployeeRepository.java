package com.example.employeemanager.repository.impl;

import com.example.employeemanager.dto.EmployeeDTO;
import com.example.employeemanager.dto.EmployeeSearchDTO;
import com.example.employeemanager.model.Employee;
import com.example.employeemanager.repository.IEmployeeRepository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EmployeeRepository implements IEmployeeRepository {

    @Override
    public List<EmployeeDTO> search(EmployeeSearchDTO employeeSearchDTO) {
        List<EmployeeDTO> employeeList = new ArrayList<>();
        try {
            StringBuilder stringBuilder = new StringBuilder(
                    "select e.id, e.name, e.birth_date, e.gender, e.salary, e.phone_number, e.department_id, d.name 'department_name'\n" +
                            "from employee e left join department d on e.department_id = d.id");

            // Tìm kiếm theo tên.
            stringBuilder.append(" where e.name like concat('%', ?, '%')");

            // Tìm kiến theo số điện thoại.
            stringBuilder.append(" and e.phone_number like  concat('%', ?, '%')");

            // Tìm kiếm theo ngày sinh.
            if (employeeSearchDTO.getFromBirthDate() != null || employeeSearchDTO.getToBirthDate() != null) {
                if (employeeSearchDTO.getFromBirthDate() == null) { // Lấy ra những ngày <= toBirthDate
                    stringBuilder.append(String.format(" and e.birth_date <= '%s'", employeeSearchDTO.getToBirthDate()));
                } else if (employeeSearchDTO.getToBirthDate() == null) { // Lấy ra những ngày >= fromBithDate
                    stringBuilder.append(String.format(" and e.birth_date >= '%s'", employeeSearchDTO.getFromBirthDate()));
                } else {
                    stringBuilder.append(String.format(" and e.birth_date between '%s' and '%s'", employeeSearchDTO.getFromBirthDate(), employeeSearchDTO.getToBirthDate()));
                }
            }

            // Tìm kiếm theo giới tính.
            if (employeeSearchDTO.getGender() != null && !employeeSearchDTO.getGender().isEmpty()) {
                stringBuilder.append(String.format(" and e.gender = %s", employeeSearchDTO.getGender()));
            }

            // Tìm kiếm theo lương.
            if ("lt5".equals((employeeSearchDTO.getSalary()))) {
                stringBuilder.append("  and (e.salary < 5000000)");
            } else if ("5-10".equals((employeeSearchDTO.getSalary()))) {
                stringBuilder.append("  and (e.salary >= 5000000) and (e.salary < 10000000)");
            } else if ("10-15".equals((employeeSearchDTO.getSalary()))) {
                stringBuilder.append("  and (e.salary >= 10000000) and (e.salary < 15000000)");
            } else if ("gt15".equals((employeeSearchDTO.getSalary()))) {
                stringBuilder.append("  and (e.salary >= 15000000)");
            }

            // Tìm kiếm theo bộ phận.
            if (employeeSearchDTO.getDepartmentId() != null && !employeeSearchDTO.getDepartmentId().isEmpty()) {
                stringBuilder.append(String.format(" and e.department_id = %s", employeeSearchDTO.getDepartmentId()));
            }

            PreparedStatement preparedStatement = BaseRepository.getConnection().prepareStatement(stringBuilder.toString());

            preparedStatement.setString(1, employeeSearchDTO.getName());
            preparedStatement.setString(2, employeeSearchDTO.getPhoneNumber());
            ResultSet resultSet = preparedStatement.executeQuery();
            EmployeeDTO employeeDTO;
            while (resultSet.next()) {
                employeeDTO = new EmployeeDTO();
                employeeDTO.setId(resultSet.getInt("id"));
                employeeDTO.setName(resultSet.getString("name"));
                employeeDTO.setBirthDate(LocalDate.parse(resultSet.getString("birth_date")));
                employeeDTO.setGender(resultSet.getBoolean("gender"));
                employeeDTO.setSalary(resultSet.getDouble("salary"));
                employeeDTO.setPhoneNumber(resultSet.getString("phone_number"));
                employeeDTO.setDepartmentId(resultSet.getInt("department_id"));
                employeeDTO.setDepartmentName((resultSet.getString("department_name")));
                employeeList.add(employeeDTO);
            }
            return employeeList;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Employee findById(int id) {
        try {
            PreparedStatement preparedStatement = BaseRepository.getConnection().prepareStatement(
                    "select id, name, birth_date, gender, salary, phone_number, department_id from employee where employee.id = ?");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            Employee employee;
            if (resultSet.next()) {
                employee = new Employee();
                employee.setId(resultSet.getInt("id"));
                employee.setName(resultSet.getString("name"));
                employee.setBirthDate(LocalDate.parse(resultSet.getString("birth_date")));
                employee.setGender(resultSet.getBoolean("gender"));
                employee.setSalary(resultSet.getDouble("salary"));
                employee.setPhoneNumber(resultSet.getString("phone_number"));
                employee.setDepartmentId(resultSet.getInt("department_id"));
                return employee;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Employee findByPhoneNumber(String phoneNumber) {
        try {
            PreparedStatement preparedStatement = BaseRepository.getConnection().prepareStatement(
                    "select id, name, birth_date, gender, salary, phone_number, department_id from employee where phone_number = ?"
            );
            preparedStatement.setString(1, phoneNumber);
            ResultSet resultSet = preparedStatement.executeQuery();

            Employee employee;
            if (resultSet.next()) {
                employee = new Employee();
                employee.setId(resultSet.getInt("id"));
                employee.setName(resultSet.getString("name"));
                employee.setBirthDate(LocalDate.parse(resultSet.getString("birth_date")));
                employee.setGender(resultSet.getBoolean("gender"));
                employee.setSalary(resultSet.getDouble("salary"));
                employee.setPhoneNumber(resultSet.getString("phone_number"));
                employee.setDepartmentId(resultSet.getInt("department_id"));
                return employee;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void create(Employee employee) {
        try {
            PreparedStatement preparedStatement = BaseRepository.getConnection().prepareStatement(
                    "insert into employee(name, birth_date, gender, salary, phone_number, department_id) value (?, ?, ?, ?, ?, ?)");

            preparedStatement.setString(1, employee.getName());
            preparedStatement.setString(2, employee.getBirthDate().toString());
            preparedStatement.setBoolean(3, employee.isGender());
            preparedStatement.setDouble(4, employee.getSalary());
            preparedStatement.setString(5, employee.getPhoneNumber());
            preparedStatement.setInt(6, employee.getDepartmentId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Employee employee) {
        try {
            PreparedStatement preparedStatement = BaseRepository.getConnection().prepareStatement(
                    "update employee set name = ?, birth_date = ?, gender = ?, salary = ?, phone_number = ?, department_id = ? where id = ?");

            preparedStatement.setString(1, employee.getName());
            preparedStatement.setString(2, employee.getBirthDate().toString());
            preparedStatement.setBoolean(3, employee.isGender());
            preparedStatement.setDouble(4, employee.getSalary());
            preparedStatement.setString(5, employee.getPhoneNumber());
            preparedStatement.setInt(6, employee.getDepartmentId());
            preparedStatement.setInt(7, employee.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        try {
            PreparedStatement preparedStatement = BaseRepository.getConnection().prepareStatement(
                    "delete from employee where id = ?");
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }



}
