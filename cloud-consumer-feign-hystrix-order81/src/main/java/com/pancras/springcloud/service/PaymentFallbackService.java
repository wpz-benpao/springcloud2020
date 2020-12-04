package com.pancras.springcloud.service;

import org.springframework.stereotype.Component;

/**
 * 解决服务降级fallback方法和业务代码逻辑混在一起，降低耦合度
 */
@Component
public class PaymentFallbackService implements PaymentHystrixService {
    @Override
    public String paymentInfo_OK(Integer id) {
        return "PaymentFallbackService--->paymentInfo_OK";
    }

    @Override
    public String paymentInfo_TimeOut(Integer id) {
        return "PaymentFallbackService--->paymentInfo_TimeOut";
    }
}
