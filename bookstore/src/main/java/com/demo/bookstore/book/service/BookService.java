package com.demo.bookstore.book.service;

import com.demo.bookstore.PageBean;
import com.demo.bookstore.book.dao.IBookDao;
import com.demo.bookstore.book.domain.Book;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class BookService {
    private IBookDao bookDao;
    public List<Book> findAll() throws BookException {
        try {
            InputStream in= Resources.getResourceAsStream("SqlMapConfig.xml");
            SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);
            SqlSession sqlSession = factory.openSession(true);
            bookDao = sqlSession.getMapper(IBookDao.class);
            List<Book> bookList = bookDao.findAll();
            sqlSession.close();
            in.close();
            return bookList;
        } catch (IOException e) {
            //e.printStackTrace();
            throw new BookException("找不到配置文件");
        }
    }

    public List<Book> findByCategory(String cid) throws BookException {
        try {
            InputStream in= Resources.getResourceAsStream("SqlMapConfig.xml");
            SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);
            SqlSession sqlSession = factory.openSession(true);
            bookDao = sqlSession.getMapper(IBookDao.class);
            List<Book> bookList = bookDao.findByCategory(cid);
            sqlSession.close();
            in.close();
            return bookList;
        } catch (IOException e) {
            //e.printStackTrace();
            throw new BookException("找不到配置文件");
        }

    }

    public Book load(String bid) throws BookException {
        try {
            InputStream in= Resources.getResourceAsStream("SqlMapConfig.xml");
            SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);
            SqlSession sqlSession = factory.openSession(true);
            bookDao = sqlSession.getMapper(IBookDao.class);
            Book book = bookDao.load(bid);
            sqlSession.close();
            in.close();
            return book;
        } catch (IOException e) {
            //e.printStackTrace();
            throw new BookException("找不到配置文件");
        }
    }

    public PageBean<Book> findAll(int pc, int ps) throws BookException{
        try {
            PageBean<Book> pb=new PageBean<Book>();
            pb.setPc(pc);
            pb.setPs(ps);
            InputStream in= Resources.getResourceAsStream("SqlMapConfig.xml");
            SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);
            SqlSession sqlSession = factory.openSession(true);
            bookDao = sqlSession.getMapper(IBookDao.class);
            int count = bookDao.getCount();
            pb.setTr(count);
            List<Book> bookList=bookDao.page((pc-1)*ps,ps);
            pb.setBeanList(bookList);
            sqlSession.close();
            in.close();
            return pb;
        } catch (IOException e) {
            //e.printStackTrace();
            throw new BookException("找不到配置文件");
        }
    }

    public void add(Book book)  throws BookException{
        try {

            InputStream in= Resources.getResourceAsStream("SqlMapConfig.xml");
            SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);
            SqlSession sqlSession = factory.openSession(true);
            bookDao = sqlSession.getMapper(IBookDao.class);
            bookDao.add(book);
            sqlSession.close();
            in.close();
        } catch (IOException e) {
            //e.printStackTrace();
            throw new BookException("找不到配置文件");
        }
    }

    public PageBean<Book> findByCategory(String cid, int pc, int ps) throws BookException {
        PageBean<Book> pb=new PageBean<Book>();
        pb.setPs(ps);
        pb.setPc(pc);
        try {

            InputStream in= Resources.getResourceAsStream("SqlMapConfig.xml");
            SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);
            SqlSession sqlSession = factory.openSession(true);
            bookDao = sqlSession.getMapper(IBookDao.class);
            int count = bookDao.getCountByCategory(cid);
            pb.setTr(count);
            List<Book> bookList=bookDao.findBookPageByCategory(cid,(pc - 1) * ps,ps);
            pb.setBeanList(bookList);
            sqlSession.close();
            in.close();
            return pb;
        } catch (IOException e) {
            //e.printStackTrace();
            throw new BookException("找不到配置文件");
        }
    }

    public void del(String bid) throws BookException {
        try {

            InputStream in= Resources.getResourceAsStream("SqlMapConfig.xml");
            SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);
            SqlSession sqlSession = factory.openSession(true);
            bookDao = sqlSession.getMapper(IBookDao.class);
            bookDao.del(bid);
            sqlSession.close();
            in.close();
        } catch (IOException e) {
            //e.printStackTrace();
            throw new BookException("找不到配置文件");
        }
    }
}
