package com.demo.bookstore.order.web.servlet.admin;

import com.demo.bookstore.order.domain.Order;
import com.demo.bookstore.order.service.OrderException;
import com.demo.bookstore.order.service.OrderService;
import cn.itcast.servlet.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

//@WebServlet("/admin/AdminOrderServlet")
public class AdminOrderServlet extends BaseServlet {
    private OrderService orderService=new OrderService();
    public String findAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Order> orderList = null;
        try {
            orderList = orderService.findAll();
        } catch (OrderException e) {
            e.printStackTrace();
        }
        request.setAttribute("orderList",orderList);
        return "f:/adminjsps/admin/order/list.jsp";
    }

    public String findByStatus(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String status = request.getParameter("status");
        List<Order> orderList= null;
        try {
            orderList = orderService.findByStatus(Integer.parseInt(status));
        } catch (OrderException e) {
            e.printStackTrace();
        }
        request.setAttribute("orderList",orderList);
        return "f:/adminjsps/admin/order/list.jsp";
    }

    public String load(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String oid = request.getParameter("oid");
        Order order = null;
        try {
            order = orderService.load(oid);
        } catch (OrderException e) {
            e.printStackTrace();
        }
        request.setAttribute("order",order);
        return "f:/adminjsps/admin/order/desc.jsp";
    }

    public String cancel(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String oid = request.getParameter("oid");

        int state=-1;
        try {
            state=orderService.findState(oid);
        } catch (OrderException e) {
            //e.printStackTrace();
            request.setAttribute("msg",e.getMessage());
            return "f:/adminjsps/msg.jsp";
        }

        if(state!=1){
            request.setAttribute("msg","訂單狀態錯誤,不能取消");
            return "f:/adminjsps/msg.jsp";
        }

        try {
            orderService.updateState(oid,5);
        } catch (OrderException e) {
            request.setAttribute("msg",e.getMessage());
            return "f:/adminjsps/msg.jsp";
        }
        request.setAttribute("msg","你的訂單已取消");
        return "f:/adminjsps/msg.jsp";
    }

    public String deliver(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String oid = request.getParameter("oid");

        int state=-1;
        try {
            state=orderService.findState(oid);
        } catch (OrderException e) {
            //e.printStackTrace();
            request.setAttribute("msg",e.getMessage());
            return "f:/adminjsps/msg.jsp";
        }

        if(state!=2){
            request.setAttribute("msg","訂單狀態錯誤");
            return "f:/adminjsps/msg.jsp";
        }

        try {
            orderService.updateState(oid,3);
        } catch (OrderException e) {
            request.setAttribute("msg",e.getMessage());
            return "f:/adminjsps/msg.jsp";
        }
        request.setAttribute("msg","你的訂單已發貨,請查看物流");
        return "f:/adminjsps/msg.jsp";
    }
}
