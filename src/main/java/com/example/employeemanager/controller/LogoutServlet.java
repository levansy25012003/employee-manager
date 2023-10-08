package com.example.employeemanager.controller;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.net.URLEncoder;

@WebServlet(name = "LogoutServlet", value = "/logout")
public class LogoutServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getSession().invalidate(); // Vô hiệu hóa session.
        Cookie cookie = new Cookie("remember-me", "");
        cookie.setMaxAge(0); // Xóa cookies
        response.addCookie(cookie);

        response.sendRedirect("/login?message=" + URLEncoder.encode("Đăng xuất thành công.", "UTF-8"));
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
