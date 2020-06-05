package com.demo.bookstore.order.dao;

import com.demo.bookstore.order.domain.Order;
import com.demo.bookstore.order.domain.OrderItem;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

public interface IOrderDao {

    @Insert("insert into orders(oid,ordertime,total,state,uid) values(#{oid},#{ordertime,jdbcType=TIMESTAMP},#{total},#{state},#{uid})")
    void addOrder(Order order);

    @Insert("<script>"+ "insert into orderitem(iid,count,subtotal,oid,bid) values"+
            "<foreach collection='orderItemList' item='item' index='index' separator=','>"+
            "(#{item.iid},#{item.count},#{item.subtotal},#{item.oid},#{item.bid})"+
            "</foreach>"+
            "</script>"
    )
    void addOrderItemList(@Param("orderItemList") List<OrderItem> orderItemList);

    @Select("select * from orders where uid=#{uid}")
    @ResultMap("orderMap")
    List<Order> findByUid(@Param("uid") String uid);

    @Select("select * from orders where oid=#{oid}")
    @Results(id="orderMap",value = {
            @Result(id=true, property = "oid",column = "oid"),
            @Result(property = "total",column ="total" ),
            @Result(property = "state",column ="state" ),
            @Result(property = "uid",column ="uid" ),
            @Result(property = "address",column ="address" ),
            @Result(property = "user",column ="uid",one=@One(select = "com.demo.bookstore.user.dao.IUserDao.findByuid",fetchType = FetchType.EAGER)),
            @Result(property = "orderItemList",column ="oid",many = @Many(select = "com.demo.bookstore.order.dao.IOrderDao.findByOid",fetchType = FetchType.LAZY))
    })
    Order load(@Param("oid") String oid);

    @Select("select * from orderitem where oid=#{oid}")
    @Results(id="orderitemMap",value = {
            @Result(id=true, property = "iid",column = "iid"),
            @Result(property = "count",column ="count" ),
            @Result(property = "subtotal",column ="subtotal" ),
            @Result(property = "oid",column ="oid" ),
            @Result(property = "bid",column ="bid" ),
            @Result(property = "book",column ="bid",one=@One(select = "com.demo.bookstore.book.dao.IBookDao.load",fetchType = FetchType.EAGER)),
            @Result(property = "order",column ="oid",one=@One(select = "com.demo.bookstore.order.dao.IOrderDao.load",fetchType = FetchType.EAGER))
    })
    List<OrderItem> findByOid(@Param("oid") String oid);

    @Update("update orders set state=#{state} where oid=#{oid}")
    void updateState(@Param("oid") String oid,@Param("state") int state);

    @Select("select * from orders")
    @ResultMap("orderMap")
    List<Order> findAll();

    @Select("select * from orders where state=#{state}")
    @ResultMap("orderMap")
    List<Order> findByStatus(@Param("state") int status);

    @Select("select state from orders where oid=#{oid}")
    int getStateByOid(@Param("oid") String oid);
}
