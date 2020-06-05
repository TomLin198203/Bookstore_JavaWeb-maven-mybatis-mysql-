package com.demo.bookstore.category.web.servlet.admin;

import com.demo.bookstore.category.domain.Category;
import com.demo.bookstore.category.service.CategoryException;
import com.demo.bookstore.category.service.CategoryService;
import cn.itcast.commons.CommonUtils;
import cn.itcast.servlet.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

//@WebServlet("/AdminCategoryServlet")
public class AdminCategoryServlet extends BaseServlet {
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
        return "f:/adminjsps/admin/category/list.jsp";
    }

    public String delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String cid =(String) request.getAttribute("cid");
        try {
            categoryService.delete(cid);
        } catch (CategoryException e) {
            //e.printStackTrace();
            request.setAttribute("msg",e.getMessage());
        }
        List<Category> list = null;
        try {
            list = categoryService.findAll();
        } catch (CategoryException e) {
            e.printStackTrace();
        }
        request.setAttribute("categoryList",list);

        return "f:/adminjsps/admin/category/list.jsp";
    }

    public String editParentPre(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String cid =(String) request.getAttribute("cid");
        try {
            Category category=categoryService.load(cid);
            request.setAttribute("category",category);
        } catch (CategoryException e) {
            //e.printStackTrace();
            request.setAttribute("msg",e.getMessage());
        }
        return "f:/adminjsps/admin/category/edit.jsp";
    }

    public String editParent(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Category category = CommonUtils.toBean(request.getParameterMap(), Category.class);
        try {
            categoryService.updateCname(category);
        } catch (CategoryException e) {
            //e.printStackTrace();
            request.setAttribute("msg",e.getMessage());
        }
        return findAll(request,response);
    }

}
