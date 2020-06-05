package com.demo.bookstore.book.dao;

import com.demo.bookstore.book.domain.Book;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface IBookDao {

    @Select("select * from book where del=0")
    List<Book> findAll();

    @Select("select * from book where cid=#{cid} and del=0")
    List<Book> findByCategory(@Param("cid") String cid);

    @Select("select * from book where bid=#{bid}")
    Book load(@Param("bid")String bid);

    @Select("select count(*) from book where cid=#{cid} and del=0")
    int getCountByCid(@Param("cid") String cid);

    @Select("select count(*) from book where del=0")
    int getCount();

    @Select("select * from book where del=0 limit #{param1},#{pc}")
    List<Book> page(@Param("param1") int param1 ,@Param("pc") int pc);

    @Insert("insert into book(bid,bname,price,author,image,cid,del) values(#{bid},#{bname},#{price},#{author},#{image},#{cid},0)")
    void add(Book book);

    @Select("select count(*) from book where cid=#{cid} and del=0")
    int getCountByCategory(@Param("cid") String cid);

    @Select("select * from book where cid=#{cid} and del=false limit #{param1},#{ps}")
    List<Book> findBookPageByCategory(@Param("cid") String cid,@Param("param1") int param1,@Param("ps") int ps);

    @Update("update book set del=1 where bid=#{bid}")
    void del(@Param("bid") String bid);
}
