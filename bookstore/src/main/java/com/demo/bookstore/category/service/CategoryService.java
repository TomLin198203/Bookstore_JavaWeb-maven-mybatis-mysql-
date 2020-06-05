package com.demo.bookstore.category.service;

import com.demo.bookstore.book.dao.IBookDao;
import com.demo.bookstore.category.dao.ICategoryDao;
import com.demo.bookstore.category.domain.Category;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class CategoryService {
    private ICategoryDao categoryDao;
    private IBookDao bookDao;
    public List<Category> findAll() throws CategoryException {
        InputStream in= null;
        try {
            in = Resources.getResourceAsStream("SqlMapConfig.xml");
            SqlSessionFactoryBuilder builder=new SqlSessionFactoryBuilder();
            SqlSessionFactory factory=builder.build(in);
            SqlSession sqlSession = factory.openSession(true);
            categoryDao = sqlSession.getMapper(ICategoryDao.class);
            List<Category> all = categoryDao.findAll();
            sqlSession.close();
            in.close();
            return all;
        } catch (IOException e) {
            throw new CategoryException("找不到配置文件");
        }

    }

    public void delete(String cid) throws CategoryException{
        InputStream in= null;
        try {
            in = Resources.getResourceAsStream("SqlMapConfig.xml");
            SqlSessionFactoryBuilder builder=new SqlSessionFactoryBuilder();
            SqlSessionFactory factory=builder.build(in);
            SqlSession sqlSession = factory.openSession(true);
            bookDao = sqlSession.getMapper(IBookDao.class);
            categoryDao = sqlSession.getMapper(ICategoryDao.class);
            int count = bookDao.getCountByCid(cid);
            if(count>0) throw new CategoryException("該類下還有圖書,不能刪除該類");
            categoryDao.deleteByCid(cid);
            sqlSession.close();
            in.close();

        } catch (IOException e) {
            throw new CategoryException("找不到配置文件");
        }
    }

    public Category load(String cid) throws CategoryException {
        InputStream in= null;
        try {
            in = Resources.getResourceAsStream("SqlMapConfig.xml");
            SqlSessionFactoryBuilder builder=new SqlSessionFactoryBuilder();
            SqlSessionFactory factory=builder.build(in);
            SqlSession sqlSession = factory.openSession(true);
            categoryDao = sqlSession.getMapper(ICategoryDao.class);
            Category category = categoryDao.load(cid);
            sqlSession.close();
            in.close();
            return category;

        } catch (IOException e) {
            throw new CategoryException("找不到配置文件");
        }
    }

    public void updateCname(Category category) throws CategoryException {
        InputStream in= null;
        try {
            in = Resources.getResourceAsStream("SqlMapConfig.xml");
            SqlSessionFactoryBuilder builder=new SqlSessionFactoryBuilder();
            SqlSessionFactory factory=builder.build(in);
            SqlSession sqlSession = factory.openSession(true);
            categoryDao = sqlSession.getMapper(ICategoryDao.class);
            categoryDao.updateCname(category);
            sqlSession.close();
            in.close();
        } catch (IOException e) {
            throw new CategoryException("找不到配置文件");
        }
    }
}
