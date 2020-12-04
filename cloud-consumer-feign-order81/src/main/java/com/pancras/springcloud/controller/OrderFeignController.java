package com.pancras.springcloud.controller;

import com.pancras.springcloud.entities.CommonResult;
import com.pancras.springcloud.service.PaymentFeignService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class OrderFeignController {
    @Resource
    private PaymentFeignService paymentFeignService;

    @GetMapping("/consumer/payment/get/{id}")
    public CommonResult getPaymentById(@PathVariable("id") Long id){
        CommonResult result = paymentFeignService.getPaymentById(id);
        return result;
    }

    @GetMapping("/consumer/payment/feign/timeout")
    public String FeignTimeOut(){
        return paymentFeignService.FeignTimeOut();
    }
}
