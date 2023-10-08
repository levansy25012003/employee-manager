package com.example.employeemanager.controller;

import com.example.employeemanager.dto.EmployeeSearchDTO;
import com.example.employeemanager.model.Employee;
import com.example.employeemanager.service.IDepartmentService;
import com.example.employeemanager.service.IEmployeeService;
import com.example.employeemanager.service.impl.DepartmentService;
import com.example.employeemanager.service.impl.EmployeeService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "ServletEmployee", value = "/employees")
public class ServletEmployee extends HttpServlet {

    IEmployeeService employeeService = new EmployeeService();
    IDepartmentService departmentService = new DepartmentService();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "create": showCreateForm(request, response); break;
            case "edit": showEditForm(request, response); break;
            case "delete": deleteEmployee(request, response); break;
            case "detail": viewEmployee(request, response); break;
            default:
                listEmployees(request, response);
        }
    }

    private void listEmployees(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        EmployeeSearchDTO employeeSearchDTO = new EmployeeSearchDTO();
        employeeSearchDTO.setName(request.getParameter("name"));
        employeeSearchDTO.setGender(request.getParameter("gender"));
        employeeSearchDTO.setDepartmentId(request.getParameter("departmentId"));
        employeeSearchDTO.setFromBirthDate(request.getParameter("fromBirthDate"));
        employeeSearchDTO.setSalary(request.getParameter("salary"));
        employeeSearchDTO.setToBirthDate(request.getParameter("toBirthDate"));
        employeeSearchDTO.setPhoneNumber(request.getParameter("phoneNumber"));

        request.setAttribute("employeeSearchDTO", employeeSearchDTO);
        request.setAttribute("employeeList", employeeService.search(employeeSearchDTO));
        request.setAttribute("departmentList", departmentService.findAll());
        request.getRequestDispatcher("employee/list.jsp").forward(request, response);
    }

    private void viewEmployee(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Employee employee = employeeService.findById(id);
        request.setAttribute("employee", employee);
        request.setAttribute("departmentList", departmentService.findAll());
        request.getRequestDispatcher("employee/detail.jsp").forward(request, response);
    }

    private void deleteEmployee(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        employeeService.delete(id);
        response.sendRedirect("/employees?message=" + URLEncoder.encode("Xóa thành công", "UTF-8"));

    }
    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Employee employee = employeeService.findById(id);
        request.setAttribute("id", employee.getId());
        request.setAttribute("name", employee.getName());
        request.setAttribute("birthDate", employee.getBirthDate());
        request.setAttribute("gender", employee.isGender());
        request.setAttribute("salary", new BigDecimal(employee.getSalary()));
        request.setAttribute("phoneNumber", employee.getPhoneNumber());
        request.setAttribute("departmentId", employee.getDepartmentId());
        request.setAttribute("departmentList", departmentService.findAll());

        request.getRequestDispatcher("employee/edit.jsp").forward(request, response);
    }
    private void showCreateForm(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.setAttribute("departmentList", departmentService.findAll());
        request.getRequestDispatcher("employee/create.jsp").forward(request, response);
    }

    @Override

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        String action = request.getParameter("action");
        switch (action) {
            case "create" : createEmployee(request, response); break;
            case "edit" : updateEmployee(request, response); break;
        }
    }

    private void updateEmployee(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<String, String> errorMessage = new HashMap<>();
        employeeService.validate(request ,errorMessage);

        int id = Integer.parseInt(request.getParameter("id"));
        Employee employeeCheckPhone = employeeService.findById(id);

        if (!employeeCheckPhone.getPhoneNumber().equals(request.getParameter("phoneNumber"))) {
            // Nếu số điện thoại không bằng nhau thì mới validate.
            employeeService.validateNumberPhoneExits(request, errorMessage);
        }


        if (!errorMessage.isEmpty()) {
            request.setAttribute("id", request.getParameter("id"));
            request.setAttribute("name", request.getParameter("name"));
            request.setAttribute("birthDate", request.getParameter("birthDate"));
            request.setAttribute("gender", request.getParameter("gender"));
            request.setAttribute("salary", request.getParameter("salary"));
            request.setAttribute("phoneNumber", request.getParameter("phoneNumber"));
            request.setAttribute("departmentId", request.getParameter("departmentId"));
            request.setAttribute("errorMessage", errorMessage);
            request.setAttribute("departmentList", departmentService.findAll());
            request.getRequestDispatcher("employee/edit.jsp").forward(request, response);
            return;
        }


        String name = request.getParameter("name");
        LocalDate birthDate = LocalDate.parse(request.getParameter("birthDate"));
        Boolean gender = Boolean.parseBoolean(request.getParameter("gender"));
        Double salary = Double.parseDouble(request.getParameter("salary"));
        String phoneNumber = request.getParameter("phoneNumber");
        int departmentId = Integer.parseInt(request.getParameter("departmentId"));

        Employee employee = new Employee(id ,name, birthDate, gender, salary, phoneNumber, departmentId);
        employeeService.update(employee);
        response.sendRedirect("/employees?message=" + URLEncoder.encode("Chỉnh sửa thành công", "UTF-8"));


    }

    private void createEmployee(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<String, String> errorMessage = new HashMap<>();

        employeeService.validate(request ,errorMessage);
        employeeService.validateNumberPhoneExits(request, errorMessage);

        if (!errorMessage.isEmpty()) {
            request.setAttribute("id", request.getParameter("id"));
            request.setAttribute("name", request.getParameter("name"));
            request.setAttribute("birthDate", request.getParameter("birthDate"));
            request.setAttribute("gender", request.getParameter("gender"));
            request.setAttribute("salary", request.getParameter("salary"));
            request.setAttribute("phoneNumber", request.getParameter("phoneNumber"));
            request.setAttribute("departmentId", request.getParameter("departmentId"));
            request.setAttribute("errorMessage", errorMessage);
            request.setAttribute("departmentList", departmentService.findAll());
            request.getRequestDispatcher("employee/create.jsp").forward(request, response);
            return;
        }

        String name = request.getParameter("name");
        LocalDate birthDate = LocalDate.parse(request.getParameter("birthDate"));
        Boolean gender = Boolean.parseBoolean(request.getParameter("gender"));
        Double salary = Double.parseDouble(request.getParameter("salary"));
        String phoneNumber = request.getParameter("phoneNumber");
        int departmentId = Integer.parseInt(request.getParameter("departmentId"));

        Employee employee = new Employee(name, birthDate, gender, salary, phoneNumber, departmentId);
        employeeService.create(employee);
        response.sendRedirect("/employees?message=" + URLEncoder.encode("Thêm mới thành công", "UTF-8"));
    }
}
