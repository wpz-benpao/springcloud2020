package com.pancras.springcloud.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;



@RestController
@Slf4j
public class FlowLimitController {


    @GetMapping("/testA")
    public String testA() {
        //TimeUnit.MILLISECONDS.sleep(800);
        return "============testA";
    }

    @GetMapping("/testB")
    public String testB(){
        return "============testB";
    }


    @GetMapping("/testD")
    public String testD()
    {
        log.info("testD 异常比例");
        int age=10/0;
        //try { TimeUnit.SECONDS.sleep(1); } catch (InterruptedException e) { e.printStackTrace(); }
        //log.info("testD 测试RT");
        return "------testD";
    }

    @GetMapping("/testE")
    public String testE()
    {
        log.info("testE 测试异常数");
        int age=10/0;
        return "------testD";
    }

    @GetMapping("/testHotKey")
    @SentinelResource(value = "testHotKey",blockHandler = "deal_testHotKey")
    public String testHotKey(@RequestParam(value = "p1",required = false) String p1,
                             @RequestParam(value = "p2",required = false) String p2){
        return "+++++++++++++++++testHotKey 测试热点限流";
    }

    public String deal_testHotKey(String p1, String p2, BlockException e){
        return "+++++++++++++++++deal_testHotKey 热点限流兜底方法";
    }

}
