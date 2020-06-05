package com.demo.bookstore.order.web.servlet;

import com.demo.bookstore.cart.domain.Cart;
import com.demo.bookstore.cart.domain.CartItem;
import com.demo.bookstore.order.service.OrderException;
import com.demo.bookstore.order.service.OrderService;
import com.demo.bookstore.order.domain.Order;
import com.demo.bookstore.order.domain.OrderItem;
import com.demo.bookstore.user.domain.User;
import cn.itcast.commons.CommonUtils;
import cn.itcast.servlet.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

//@WebServlet("/OrderServlet")
public class OrderServlet extends BaseServlet {
    private OrderService orderService=new OrderService();
    public String add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cart cart =(Cart) request.getSession().getAttribute("cart");
        Order order=new Order();
        order.setOrdertime(new Date());
        order.setOid(CommonUtils.uuid());
        Collection<CartItem> cartItems = cart.getCartItems();
        User user =(User) request.getSession().getAttribute("session_user");
        order.setUid(user.getUid());
        order.setUser(user);
        order.setTotal(cart.getTotal());
        order.setState(1);

        List<OrderItem> orderItemList=new ArrayList<OrderItem>();
        for (CartItem cartItem : cartItems) {
            OrderItem orderItem=new OrderItem();
            orderItem.setBook(cartItem.getBook());
            orderItem.setIid(CommonUtils.uuid());
            orderItem.setBid(cartItem.getBook().getBid());
            orderItem.setSubtotal(cartItem.getSubtotal());
            orderItem.setCount(cartItem.getCount());
            orderItem.setOid(order.getOid());
            orderItem.setOrder(order);
            orderItemList.add(orderItem);
        }
        order.setOrderItemList(orderItemList);

        cart.clear();
        try {
            orderService.add(order);
        } catch (OrderException e) {
            e.printStackTrace();
        }
        request.setAttribute("order",order);
        return "f:/jsps/order/desc.jsp";
    }


    public String myOrders(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user =(User) request.getSession().getAttribute("session_user");
        try {
            List<Order> orderList=orderService.myOrders(user.getUid());
            request.setAttribute("orderList",orderList);
        } catch (OrderException e) {
            e.printStackTrace();
        }
        return "f:/jsps/order/list.jsp";
    }

    public String load(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String oid = request.getParameter("oid");
        try {
            Order order=orderService.load(oid);
            request.setAttribute("order",order);
        } catch (OrderException e) {
            e.printStackTrace();
        }
        return "f:/jsps/order/desc.jsp";
    }

    public String confirm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String oid = request.getParameter("oid");
        try {
            orderService.confirm(oid);
            request.setAttribute("msg","交易成功");
        } catch (OrderException e) {
            //e.printStackTrace();
            request.setAttribute("msg",e.getMessage());
        }
        return "f:/jsps/msg.jsp";
    }
}
