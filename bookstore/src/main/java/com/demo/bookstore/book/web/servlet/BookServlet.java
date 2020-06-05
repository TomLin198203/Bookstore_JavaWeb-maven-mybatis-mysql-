package com.demo.bookstore.book.web.servlet;

import com.demo.bookstore.book.domain.Book;
import com.demo.bookstore.book.service.BookException;
import com.demo.bookstore.book.service.BookService;
import cn.itcast.servlet.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

//@WebServlet("/BookServlet")
public class BookServlet extends BaseServlet {
    private BookService bookService=new BookService();
    public String findAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            List<Book> bookList=bookService.findAll();
            request.setAttribute("booklist",bookList);
        } catch (BookException e) {
            e.printStackTrace();
        }
        return "f:/jsps/book/list.jsp";
    }


    public String findByCategory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String cid = request.getParameter("cid");
        try {
            List<Book> bookList=bookService.findByCategory(cid);
            request.setAttribute("booklist",bookList);
        } catch (BookException e) {
            e.printStackTrace();
        }
        return "f:/jsps/book/list.jsp";
    }

    public String load(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String bid = request.getParameter("bid");
        try {
            Book book=bookService.load(bid);
            request.setAttribute("book",book);
        } catch (BookException e) {
            e.printStackTrace();
        }
        return "f:/jsps/book/desc.jsp";
    }
}
