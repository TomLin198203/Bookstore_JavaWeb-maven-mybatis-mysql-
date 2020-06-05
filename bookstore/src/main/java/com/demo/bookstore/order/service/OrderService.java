package com.demo.bookstore.order.service;


import com.demo.bookstore.order.dao.IOrderDao;
import com.demo.bookstore.order.domain.Order;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class OrderService {
    private IOrderDao orderDao;
    public void add(Order order) throws OrderException {
        try {
            InputStream in= Resources.getResourceAsStream("SqlMapConfig.xml");
            SqlSessionFactoryBuilder builder=new SqlSessionFactoryBuilder();
            SqlSessionFactory factory = builder.build(in);
            SqlSession sqlSession = factory.openSession(true);
            orderDao=sqlSession.getMapper(IOrderDao.class);
            orderDao.addOrder(order);
            orderDao.addOrderItemList(order.getOrderItemList());
            sqlSession.close();
            in.close();
        } catch (IOException e) {
            throw new OrderException("找不到配置文件");
        }

    }

    public List<Order> myOrders(String uid) throws OrderException{
        try {
            InputStream in= Resources.getResourceAsStream("SqlMapConfig.xml");
            SqlSessionFactoryBuilder builder=new SqlSessionFactoryBuilder();
            SqlSessionFactory factory = builder.build(in);
            SqlSession sqlSession = factory.openSession(true);
            orderDao=sqlSession.getMapper(IOrderDao.class);
            List<Order> orderList = orderDao.findByUid(uid);
            sqlSession.close();
            in.close();
            return orderList;
        } catch (IOException e) {
            throw new OrderException("找不到配置文件");
        }
    }

    public Order load(String oid) throws OrderException {
        try {
            InputStream in= Resources.getResourceAsStream("SqlMapConfig.xml");
            SqlSessionFactoryBuilder builder=new SqlSessionFactoryBuilder();
            SqlSessionFactory factory = builder.build(in);
            SqlSession sqlSession = factory.openSession(true);
            orderDao=sqlSession.getMapper(IOrderDao.class);
            Order order = orderDao.load(oid);
            sqlSession.close();
            in.close();
            return order;
        } catch (IOException e) {
            throw new OrderException("找不到配置文件");
        }
    }

    public void confirm(String oid) throws OrderException {
        try {
            InputStream in= Resources.getResourceAsStream("SqlMapConfig.xml");
            SqlSessionFactoryBuilder builder=new SqlSessionFactoryBuilder();
            SqlSessionFactory factory = builder.build(in);
            SqlSession sqlSession = factory.openSession(true);
            orderDao=sqlSession.getMapper(IOrderDao.class);
            Order order = orderDao.load(oid);
            if(order.getState()!=3) throw new OrderException("訂單狀態異常");
            orderDao.updateState(oid,4);
            sqlSession.close();
            in.close();

        } catch (IOException e) {
            throw new OrderException("找不到配置文件");
        }
    }

    public List<Order> findAll() throws OrderException {
        try {
            InputStream in= Resources.getResourceAsStream("SqlMapConfig.xml");
            SqlSessionFactoryBuilder builder=new SqlSessionFactoryBuilder();
            SqlSessionFactory factory = builder.build(in);
            SqlSession sqlSession = factory.openSession(true);
            orderDao=sqlSession.getMapper(IOrderDao.class);
            List<Order> orderList = orderDao.findAll();
            for (Order order : orderList) {
                System.out.println(order);
            }
            sqlSession.close();
            in.close();
            return orderList;

        } catch (IOException e) {
            throw new OrderException("找不到配置文件");
        }
    }

    public List<Order> findByStatus(int status) throws OrderException {
        try {
            InputStream in= Resources.getResourceAsStream("SqlMapConfig.xml");
            SqlSessionFactoryBuilder builder=new SqlSessionFactoryBuilder();
            SqlSessionFactory factory = builder.build(in);
            SqlSession sqlSession = factory.openSession(true);
            orderDao=sqlSession.getMapper(IOrderDao.class);
            List<Order> orderList = orderDao.findByStatus(status);
            sqlSession.close();
            in.close();
            return orderList;
        } catch (IOException e) {
            throw new OrderException("找不到配置文件");
        }
    }

    public int findState(String oid) throws OrderException{
        try {
            InputStream in= Resources.getResourceAsStream("SqlMapConfig.xml");
            SqlSessionFactoryBuilder builder=new SqlSessionFactoryBuilder();
            SqlSessionFactory factory = builder.build(in);
            SqlSession sqlSession = factory.openSession(true);
            orderDao=sqlSession.getMapper(IOrderDao.class);
            int state = orderDao.getStateByOid(oid);
            sqlSession.close();
            in.close();
            return state;
        } catch (IOException e) {
            throw new OrderException("找不到配置文件");
        }
    }

    public void updateState(String oid, int i) throws OrderException {
        try {
            InputStream in= Resources.getResourceAsStream("SqlMapConfig.xml");
            SqlSessionFactoryBuilder builder=new SqlSessionFactoryBuilder();
            SqlSessionFactory factory = builder.build(in);
            SqlSession sqlSession = factory.openSession(true);
            orderDao=sqlSession.getMapper(IOrderDao.class);
            orderDao.updateState(oid,i);
            sqlSession.close();
            in.close();
        } catch (IOException e) {
            throw new OrderException("找不到配置文件");
        }
    }
}
