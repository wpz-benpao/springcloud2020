package com.pancras.springcloud.controller;

import com.pancras.springcloud.entities.CommonResult;
import com.pancras.springcloud.entities.Payment;
import com.pancras.springcloud.service.PaymentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class CircleBreakerController {
    @Resource
    private PaymentService paymentService;

    @GetMapping("/consumer/openFeign/paymentSQL/{id}")
    public CommonResult<Payment> paymentSQL(@PathVariable("id") Long id){
        return paymentService.paymentSQL(id);
    }
}
