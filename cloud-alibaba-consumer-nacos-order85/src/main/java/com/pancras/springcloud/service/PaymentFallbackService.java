package com.pancras.springcloud.service;

import com.pancras.springcloud.entities.CommonResult;
import com.pancras.springcloud.entities.Payment;
import org.springframework.stereotype.Service;

/**
 * 这个类是为了解决PaymentService 中的服务调用如果出现异常，则执行这里的兜底方法
 */
@Service
public class PaymentFallbackService implements PaymentService {
    @Override
    public CommonResult<Payment> paymentSQL(Long id) {
        return new CommonResult<Payment>(444,"服务降级返回---PaymentFallbackService",new Payment(id,"errorSerial"));
    }
}
