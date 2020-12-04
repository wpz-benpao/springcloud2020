package com.pancras.springcloud.service.impl;

import com.pancras.springcloud.dao.OrderDao;
import com.pancras.springcloud.domain.Order;
import com.pancras.springcloud.service.AccountService;
import com.pancras.springcloud.service.OrderService;
import com.pancras.springcloud.service.StorageService;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Resource
    private OrderDao orderDao;
    @Resource
    private AccountService accountService;
    @Resource
    private StorageService storageService;

    //GlobalTransactional全局事务配置，作用一个服务链路的入口服务方法上
    // 在rollbackFor = Exception.class表示发生了任何异常都回滚
    @GlobalTransactional(name = "fsp-create-order",rollbackFor = Exception.class)
    @Override
    public void create(Order order) {
        //1.新建订单
        log.info("------------>开始新建订单");
        orderDao.create(order);

        //2.扣减库存
        log.info("------------>订单微服务开始调用库存count做扣减");
        storageService.decrease(order.getProductId(),order.getCount());
        log.info("------------>订单微服务做库存count扣减结束");

        //3.扣减账户
        log.info("------------>订单微服务开始调用账户money做扣减");
        accountService.decrease(order.getUserId(),order.getMoney());
        log.info("------------>订单微服务做账户money扣减结束");

        //4.修改订单的状态，从0(创建订单)---->1(订单创建完结)
        log.info("------------>修改订单状态开始");
        orderDao.update(order.getUserId(),0);
        log.info("------------>修改订单状态结束");

        log.info("------------>下订单服务已完成");

    }

    @Override
    public void update(Long userId, Integer status) {
        orderDao.update(userId,status);
    }
}
