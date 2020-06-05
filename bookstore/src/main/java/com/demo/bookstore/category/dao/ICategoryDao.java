package com.demo.bookstore.category.dao;

import com.demo.bookstore.category.domain.Category;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface ICategoryDao {

    @Select("select * from category")
    List<Category> findAll();

    @Delete("delete from category where cid=#{cid")
    void deleteByCid(@Param("cid") String cid);

    @Select("select * from category where cid=#{cid}")
    Category load(@Param("cid") String cid);

    @Update("update category set cname=#{cname} where cid=#{cid}")
    void updateCname(Category category);
}
