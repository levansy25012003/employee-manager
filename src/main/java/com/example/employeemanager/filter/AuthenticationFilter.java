package com.example.employeemanager.filter;

import com.example.employeemanager.dto.UserDetail;
import com.example.employeemanager.service.impl.UserService;
import com.example.employeemanager.uitil.JWTUtil;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.URLEncoder;

@WebFilter(filterName = "AuthenticationFilter") // * tất cả các request
public class AuthenticationFilter implements Filter {
    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        UserService userService = new UserService();

        HttpSession session = httpRequest.getSession();
        String urlPattern = httpRequest.getServletPath();

        // Trường hợp mới vào cú đầu tiên.
        if("/login".equals(urlPattern)) {
            chain.doFilter(request, response);
            return;
        }

        // Nếu như gặp không có session => tìm xem có cookie ko.
        if(session.getAttribute("userDetail") == null) {
            boolean isAutoLogin = false;
            Cookie[] cookies = httpRequest.getCookies();
            if(cookies != null) {
                for (Cookie cookie : cookies) {
                    if ("remember-me".equals(cookie.getName())) {
                        isAutoLogin = true;
                        String token = cookie.getValue(); // Lấy cái vlue của cookie.
                        String username = JWTUtil.getUserNameFromJwtToken(token);

                        UserDetail userDetail = new UserDetail(username, userService.findRolesByUsername(username));
                        session.setAttribute("userDetail", userDetail); // DÙng để hiển thị chữ quangnn á.
                    }
                }
            }

            if (!isAutoLogin) { // Không có cookie.
                httpResponse.sendRedirect("/login?message=" + URLEncoder.encode("Bạn chưa đăng nhập?", "UTF-8"));
                return;
            }
        }
        chain.doFilter(request, response); // Cho vào servlet.
    }
}
