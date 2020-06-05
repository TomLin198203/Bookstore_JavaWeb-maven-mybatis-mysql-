package com.demo.bookstore.admin.web.servlet;

import com.demo.bookstore.admin.domain.Admin;
import com.demo.bookstore.admin.service.AdminException;
import com.demo.bookstore.admin.service.AdminService;
import cn.itcast.commons.CommonUtils;
import cn.itcast.servlet.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//@WebServlet("/AdminServlet")
public class AdminServlet extends BaseServlet {
    private AdminService adminService=new AdminService();

    public String login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Admin form = CommonUtils.toBean(request.getParameterMap(), Admin.class);
        try {
            Admin admin=adminService.login(form);
            request.getSession().setAttribute("admin",admin);
            request.setAttribute("msg",admin.getAdminname()+",登入成功");
        } catch (AdminException e) {
            //e.printStackTrace();
            request.setAttribute("msg",e.getMessage());
            return "f:/adminjsps/login.jsp";
        }
        return "f:/adminjsps/admin/index.jsp";
    }
}
