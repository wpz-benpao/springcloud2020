package com.pancras.springcloud.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.pancras.springcloud.entities.CommonResult;
import com.pancras.springcloud.entities.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
@Slf4j
public class CircleBreakerController {

    public static final String SERVICE_URL="http://nacos-payment-provider";

    @Resource
    private RestTemplate restTemplate;

    @GetMapping("/consumer/fallback/{id}")
    //@SentinelResource(value = "fallback")   //没有配置,向用户抛出异常错误页面，非常不友好
    //@SentinelResource(value = "fallback",fallback = "handlerFallback") //只负责业务异常
    //@SentinelResource(value = "fallback",blockHandler = "blockHandler")  //只负责控制台的配置违规

    //负责业务异常和配置违规异常
    //exceptionsToIgnore表示忽略某一个异常，如果有该异常，不再有fallback兜底方法，没有降级效果了
    /*@SentinelResource(value = "fallback",fallback = "handlerFallback",
                      blockHandler = "blockHandler",
                      exceptionsToIgnore = {IllegalArgumentException.class})*/
    @SentinelResource(value = "fallback",fallback = "handlerFallback",
                      blockHandler = "blockHandler")
    public CommonResult<Payment> fallback(@PathVariable("id") Long id){
        CommonResult<Payment> result = restTemplate.getForObject(SERVICE_URL + "/paymentSQL/" + id, CommonResult.class, id);
        if (id==4){
            throw new IllegalArgumentException("IllegalArgumentException,非法参数异常。。。");
        }else if (result.getData()==null){
            throw new NullPointerException("NullPointerException,该ID没有对应记录，空指针异常");
        }
        return result;
    }

    //兜底方法，fallback可以管理运行异常，给用户提供良好的提示，相当于Hystrix服务降级
    public CommonResult handlerFallback(Long id,Throwable e){
        Payment payment=new Payment(id ,"null");
        return new CommonResult(444,"兜底异常handlerFallback，exception内容："+e.getMessage(),payment);
    }

    //兜底方法，blockHandler可以管理sentinel配置违规异常，给用户提供良好的提示
    public CommonResult blockHandler(Long id, BlockException blockException){
        Payment payment=new Payment(id ,"null");
        return new CommonResult(555,"blockHandler-sentinel限流，blockException内容："+blockException.getMessage(),payment);
    }
}
