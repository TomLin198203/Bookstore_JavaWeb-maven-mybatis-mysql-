package com.demo.bookstore.user.dao;

import com.demo.bookstore.user.domain.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface IUserDao {
    @Select("select * from tb_user where username=#{username}")
    User findByUsername(String username);

    @Select("select * from tb_user where email=#{email}")
    User findByEmail(String email);

    @Insert("insert into tb_user(uid,username,password,email,code,state) values(#{uid},#{username},#{password},#{email},#{code},#{state})")
    void add(User user);

    @Select("select * from tb_user where code=#{code}")
    User findByCode(String code);

    @Update("update tb_user set state=#{state} where uid=#{uid}")
    void updateState(User user);

    @Select("select * from tb_user where uid=#{uid}")
    User findByuid(String uid);

}
