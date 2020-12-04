package com.pancras.springcloud.service.impl;

import com.pancras.springcloud.dao.PaymentDao;
import com.pancras.springcloud.entities.Payment;
import com.pancras.springcloud.service.PaymentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class PaymentServiceImpl implements PaymentService {
    @Resource  //javax自带的注解，自动注入
    private PaymentDao paymentDao;

    @Override
    public int create(Payment payment) {
        return paymentDao.create(payment);
    }

    @Override
    public Payment getPaymentById(Long id) {
        return paymentDao.getPaymentById(id);
    }
}
