package com.pancras.springcloud.dao;

import com.pancras.springcloud.domain.Order;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface OrderDao {
    //新建订单
    public void create(Order order);

    //修改订单状态，从0改为1
    public void update(@Param("userId") Long userId, @Param("status") Integer status);
}
