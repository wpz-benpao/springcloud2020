package com.pancras.springcloud.service;

import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;

public interface AccountService {
    /**
     * 扣减账户余额
     */
    public void decrease(@RequestParam("userId") Long userId, @RequestParam("money") BigDecimal money);

}
