package com.pancras.springcloud.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.pancras.springcloud.entities.CommonResult;
import com.pancras.springcloud.entities.Payment;
import com.pancras.springcloud.handler.CustomerBlockHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RateLimitController {
    /**
     * @SentinelResource注解的blockHandler是用来解决sentinel限流的兜底方案
     * 按照资源名名来限流，可以调用自定义的兜底方法
     * @return
     */
    @GetMapping("/byResource")
    @SentinelResource(value = "byResource",blockHandler = "handleException")
    public CommonResult byResource(){
        return new CommonResult(200,"按资源名称限流测试OK",new Payment(2020L,"serial001"));
    }
    public CommonResult handleException(BlockException e){
        return new CommonResult(444,e.getClass().getCanonicalName()+"\t 服务不可用");
    }

    /**
     * 按照路径名来限流，无法调用自定义的兜底方法，自带提示（Blocked by Sentinel）
     * @return
     */
    @GetMapping("/byUrl")
    @SentinelResource(value = "byUrl")
    public CommonResult byUrl(){
        return new CommonResult(200,"按路径名称限流测试OK",new Payment(2021L,"serial002"));
    }

    @GetMapping("/customerBlockHandler")
    @SentinelResource(value = "customerBlockHandler",
                      blockHandlerClass = CustomerBlockHandler.class,
                      blockHandler = "handlerException2")
    public CommonResult customerBlockHandler(){
        return new CommonResult(200,"限流兜底方法解耦",new Payment(2022L,"serial003"));
    }

}
