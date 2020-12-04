package com.pancras.springcloud.service;

public interface PaymentService {
    public String paymentInfo_OK(Integer id);

    public String paymentInfo_TimeOut(Integer id) throws InterruptedException;

    public String paymentInfo_TimeOutHandler(Integer id) throws InterruptedException;

    public String paymentCircuitBreaker(Integer id);

    public String paymentCircuitBreaker_fallback(Integer id);
}
