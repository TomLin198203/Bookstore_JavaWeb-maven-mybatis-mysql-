package com.demo.bookstore.book.web.servlet.admin;

import com.demo.bookstore.PageBean;
import com.demo.bookstore.book.domain.Book;
import com.demo.bookstore.book.service.BookException;
import com.demo.bookstore.book.service.BookService;
import com.demo.bookstore.category.domain.Category;
import com.demo.bookstore.category.service.CategoryException;
import com.demo.bookstore.category.service.CategoryService;
import cn.itcast.servlet.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

//@WebServlet("/AdminBookServlet")
public class AdminBookServlet extends BaseServlet {
    private BookService bookService=new BookService();
    private CategoryService categoryService=new CategoryService();
    public String findAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int ps=5;
        String pc_s = request.getParameter("pc");
        int pc;
        if (pc_s == null || pc_s.trim().isEmpty()) {
            pc=1;
        }
        else
            pc=Integer.parseInt(pc_s);

        String url=getUrl(request);
        if(url.contains("&pc=")){
            int i = url.indexOf("&pc=");
            url = url.substring(0, i);
        }

        PageBean<Book> pb = null;
        try {
            pb = bookService.findAll(pc,ps);
        } catch (BookException e) {
            e.printStackTrace();
        }

        pb.setUrl(url);
        request.setAttribute("pb",pb);
        return "f:/adminjsps/admin/book/list.jsp";
    }

    
    public String load(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String bid =request.getParameter("bid");
        Book book = null;
        try {
            book = bookService.load(bid);

        } catch (BookException e) {
            e.printStackTrace();
        }
        List<Category> categoryList=null;
        try {
            categoryList = categoryService.findAll();
        } catch (CategoryException e) {
            e.printStackTrace();
        }
        request.setAttribute("book",book);
        request.setAttribute("categoryList",categoryList);
        return "f:/adminjsps/admin/book/desc.jsp";
    }


    public String addPre(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Category> categoryList = null;
        try {
            categoryList = categoryService.findAll();
        } catch (CategoryException e) {
            e.printStackTrace();
        }
        request.setAttribute("categoryList",categoryList);
        return "f:/adminjsps/admin/book/add.jsp";
    }

    private String getUrl(HttpServletRequest request) {
        String contextPath = request.getContextPath();
        String servletPath = request.getServletPath();
        String queryString = request.getQueryString();
        return contextPath+servletPath+"?"+queryString;
    }

    private int getPc(HttpServletRequest request) {
        String pc = request.getParameter("pc");
        if(pc==null || pc.trim().isEmpty()){
            return 1;
        }
        return Integer.parseInt(pc);
    }

    public String findByCategory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String cid = request.getParameter("cid");
        int pc=getPc(request);
        int ps=5;
        String url=getUrl(request);
        if(url.contains("&pc=")){
            int i = url.indexOf("&pc=");
            url = url.substring(0, i);
        }

        System.out.println("cid:"+cid);
        PageBean<Book> pb = null;
        try {
            pb = bookService.findByCategory(cid,pc,ps);
        } catch (BookException e) {
            e.printStackTrace();
        }
        pb.setUrl(url);
        request.setAttribute("pb",pb);
        return "f:/adminjsps/admin/book/list.jsp";
    }

    public String del(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String bid = request.getParameter("bid");
        try {
            bookService.del(bid);
        } catch (BookException e) {
            e.printStackTrace();
        }
        return findAll(request,response);
    }

    public String findCategoryAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Category> list= null;
        try {
            list = categoryService.findAll();
        } catch (CategoryException e) {
            e.printStackTrace();
        }
        //System.out.println(list);
        request.setAttribute("categoryList",list);
        return "f:/adminjsps/admin/book/left.jsp";
    }
}
