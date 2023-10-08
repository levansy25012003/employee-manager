package com.example.employeemanager.filter;

import com.example.employeemanager.dto.UserDetail;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebFilter(filterName = "AuthorizationFilter")
public class AuthorizationFilter implements Filter {
    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        HttpSession session = httpRequest.getSession();

        String servletPath = httpRequest.getServletPath();
        String action = httpRequest.getParameter("action");
        if (!"/employees".equals(servletPath) || action == null || action.equals("detail")) {
            chain.doFilter(request, response);
            return;
        }

        List<String> roles = ((UserDetail) session.getAttribute("userDetail")).getRoles();
        if (!roles.contains("admin")) {
            httpResponse.sendRedirect("/acess-denied");
            return;
        }
        chain.doFilter(request, response);
    }
}
