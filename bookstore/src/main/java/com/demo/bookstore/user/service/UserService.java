package com.demo.bookstore.user.service;

import com.demo.bookstore.user.dao.IUserDao;
import com.demo.bookstore.user.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class UserService {
    private IUserDao userDao;
    public User login(User form) throws UserException{
        try {
            InputStream in= Resources.getResourceAsStream("SqlMapConfig.xml");

            SqlSessionFactoryBuilder builder=new SqlSessionFactoryBuilder();
            SqlSessionFactory factory = builder.build(in);
            SqlSession sqlSession = factory.openSession(true);
            userDao=sqlSession.getMapper(IUserDao.class);
            User user=userDao.findByUsername(form.getUsername());
            System.out.println("service user:"+user);
            if(user==null)
                throw new UserException("帳號不存在");
            if(!user.getPassword().equals(form.getPassword()))
                throw new UserException("密碼錯誤");
            sqlSession.close();
            in.close();
            return user;
        } catch (IOException e) {
            throw new UserException("找不到配置文件");
        }
    }

    public void regist(User form) throws UserException {

        try {
            System.out.println("userservice regist");
            InputStream in= Resources.getResourceAsStream("SqlMapConfig.xml");

            SqlSessionFactoryBuilder builder=new SqlSessionFactoryBuilder();
            SqlSessionFactory factory = builder.build(in);
            SqlSession sqlSession = factory.openSession(true);
            userDao=sqlSession.getMapper(IUserDao.class);
            User user=userDao.findByUsername(form.getUsername());

            if(user!=null)
                throw new UserException("此帳號已被註冊,請重新註冊");
            user=userDao.findByEmail(form.getEmail());
            if(user!=null)
                throw new UserException("此email已被註冊,請重新註冊");
            userDao.add(form);

            sqlSession.close();
            in.close();

        } catch (IOException e) {
            throw new UserException("找不到配置文件");
        }
    }

    public void active(String code) throws UserException {
        try {
            InputStream in= Resources.getResourceAsStream("SqlMapConfig.xml");

            SqlSessionFactoryBuilder builder=new SqlSessionFactoryBuilder();
            SqlSessionFactory factory = builder.build(in);
            SqlSession sqlSession = factory.openSession(true);
            userDao=sqlSession.getMapper(IUserDao.class);
            User user=userDao.findByCode(code);
            System.out.println("active user:"+user);
            if(user==null)  throw new UserException("激活碼無效");
            if(user.getState()==1) throw new UserException("此激活碼已激活過");
            user.setState(1);
            userDao.updateState(user);
            sqlSession.close();
            in.close();

        } catch (IOException e) {
            throw new UserException("找不到配置文件");
        }
    }
}
