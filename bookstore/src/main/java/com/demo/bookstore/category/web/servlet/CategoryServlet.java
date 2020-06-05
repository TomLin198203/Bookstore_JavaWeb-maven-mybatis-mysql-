package com.demo.bookstore.category.web.servlet;

import com.demo.bookstore.category.domain.Category;
import com.demo.bookstore.category.service.CategoryException;
import com.demo.bookstore.category.service.CategoryService;
import cn.itcast.servlet.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

//@WebServlet("/CategoryServlet")
public class CategoryServlet extends BaseServlet {
    private CategoryService categoryService=new CategoryService();
    public String findAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Category> categoryList = null;
        try {
            categoryList = categoryService.findAll();
        } catch (CategoryException e) {
            //e.printStackTrace();
            request.setAttribute("msg",e.getMessage());
        }
        request.setAttribute("categoryList",categoryList);
        return "f:/jsps/left.jsp";
    }
}
