package com.demo.bookstore.user.web.filter;

import com.demo.bookstore.user.domain.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

//@WebFilter(filterName = "LoginFilter")
public class LoginFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest httpRequest=(HttpServletRequest)req;

        User session_user = (User)httpRequest.getSession().getAttribute("session_user");
        System.out.println(session_user);
        if(session_user!=null)
            chain.doFilter(req, resp);
        else{
            httpRequest.setAttribute("msg","你尚未登入");
            httpRequest.getRequestDispatcher("/jsps/user/login.jsp").forward(httpRequest,resp);
        }
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
