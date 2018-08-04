package com.heven.holt.global;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(filterName = "LoginFilter", urlPatterns = {"*.jsp","*.do"})
public class LoginFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        String path = request.getServletPath();
        if (path.toLowerCase().contains("login")) {
            chain.doFilter(req, resp);
        } else {
            HttpSession session = request.getSession();
            Object obj = session.getAttribute("USER");
            if (obj != null) {
                chain.doFilter(req, resp);
            } else {
                response.sendRedirect(request.getContextPath() + "/toLogin.do");
            }
        }
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
