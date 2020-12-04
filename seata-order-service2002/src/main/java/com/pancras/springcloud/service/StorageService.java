package com.pancras.springcloud.service;

public interface StorageService {
    //扣减库存信息
    public void decrease(Long productId,Integer count);
}
