package com.demo.bookstore.cart.web.servlet;

import com.demo.bookstore.book.domain.Book;
import com.demo.bookstore.book.service.BookException;
import com.demo.bookstore.book.service.BookService;
import com.demo.bookstore.cart.domain.Cart;
import com.demo.bookstore.cart.domain.CartItem;
import cn.itcast.servlet.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//@WebServlet("/CartServlet")
public class CartServlet extends BaseServlet {

    public String add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String bid = request.getParameter("bid");
        String count = request.getParameter("count");
        BookService bookService=new BookService();
        Book book = null;
        try {
            book = bookService.load(bid);
        } catch (BookException e) {
            e.printStackTrace();
        }
        Cart cart = (Cart) request.getSession().getAttribute("cart");
        CartItem cartItem=new CartItem();
        cartItem.setCount(Integer.parseInt(count));
        cartItem.setBook(book);
        cart.add(cartItem);

        return "f:/jsps/cart/list.jsp";
    }


    public String clear(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cart cart =(Cart) request.getSession().getAttribute("cart");
        cart.clear();
        return "f:/jsps/cart/list.jsp";
    }

    public String delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String bid = request.getParameter("bid");
        Cart cart =(Cart) request.getSession().getAttribute("cart");
        cart.delete(bid);
        return "f:/jsps/cart/list.jsp";
    }
}
