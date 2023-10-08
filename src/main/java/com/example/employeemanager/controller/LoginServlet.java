package com.example.employeemanager.controller;

import com.example.employeemanager.dto.UserDetail;
import com.example.employeemanager.model.User;
import com.example.employeemanager.service.IUserService;
import com.example.employeemanager.service.impl.UserService;
import com.example.employeemanager.uitil.BCryptUtil;
import com.example.employeemanager.uitil.JWTUtil;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.net.URLEncoder;

@WebServlet(name = "LoginServlet", value = "/login")
public class LoginServlet extends HttpServlet {
    IUserService userService = new UserService();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        User user = userService.findByUsername(username);

        if (user == null || !BCryptUtil.checkPassword(password, user.getPassword())) {
            request.setAttribute("username", username);
            request.setAttribute("message", "Tên tài khoản mật khẩu không chính xác.");
            request.getRequestDispatcher("login.jsp").forward(request, response);
            return;
        }

        String rememberMe = request.getParameter("remember-me");
        if ("on".equals(rememberMe)) {
            String token = JWTUtil.generateTokenLogin(username);

            Cookie cookie = new Cookie("remember-me", token);
            cookie.setMaxAge(7 * 24 * 60 * 60);
            response.addCookie(cookie); // Thêm cookie.
        }

        // Login thành công rồi mới tới đây.
        UserDetail userDetail = new UserDetail(user.getUsername(), userService.findRolesByUsername(user.getUsername()));

        HttpSession session = request.getSession();
        session.setAttribute("userDetail", userDetail);
        response.sendRedirect("/employees");
    }
}
