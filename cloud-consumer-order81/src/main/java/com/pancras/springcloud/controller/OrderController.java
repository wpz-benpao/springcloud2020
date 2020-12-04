package com.pancras.springcloud.controller;

import com.pancras.springcloud.entities.CommonResult;
import com.pancras.springcloud.entities.Payment;
import com.pancras.springcloud.lb.LoadBalance;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.net.URI;
import java.util.List;

@RestController
@Slf4j
public class OrderController {

    //private final String PAYMENT_URL="http://localhost:8001/payment/"; 单机版
    //集群版
    private final String PAYMENT_URL="http://CLOUD-PAYMENT-SERVICE/payment/";

    @Resource
    private RestTemplate restTemplate;

    //注入自定义的负载均衡算法
    @Resource
    private LoadBalance loadBalance;

    @Resource
    private DiscoveryClient discoveryClient;

    /**
     * 这是最开始的代码，需要调用discoveryClient里面的方法才能生效
     * @param payment
     * @return
     */
    @GetMapping("/consumer/create")
    public CommonResult create(Payment payment){
        return restTemplate.postForObject(PAYMENT_URL+"create",payment,CommonResult.class);
    }

    /**
     * 这是最开始的代码，需要调用discoveryClient里面的方法才能生效
     * @param id
     * @return
     */
    @GetMapping("/consumer/get/{id}")
    public CommonResult getPayment(@PathVariable Long id){
        return restTemplate.getForObject(PAYMENT_URL+"get/"+id,CommonResult.class);
    }

    /**
     * 这是最开始的代码，需要调用discoveryClient里面的方法才能生效
     * @param id
     * @return
     */
    @GetMapping("/consumer/getForEntity/{id}")
    public ResponseEntity<CommonResult> getPayment2(@PathVariable Long id){
        ResponseEntity<CommonResult> responseEntity=restTemplate.getForEntity(PAYMENT_URL+"get/"+id,CommonResult.class);
        if (responseEntity.getStatusCode().is2xxSuccessful()){
            log.info("+++++++++++++++++++++StatusCode："+responseEntity.getStatusCode());
            log.info("+++++++++++++++++++++Body："+responseEntity.getBody());
            log.info("+++++++++++++++++++++Headers："+responseEntity.getHeaders());
        }
        return responseEntity;
    }

    @GetMapping("/consumer/payment/lb")
    public String getPaymentLB(){
        List<ServiceInstance> instances=discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        if (instances==null || instances.size()<=0){
            return null;
        }
        ServiceInstance serviceInstance=loadBalance.instance(instances);
        URI uri=serviceInstance.getUri();
        return restTemplate.getForObject(uri+"/payment/lb",String.class);
    }

    @GetMapping("/consumer/payment/zipkin")
    public String getPaymentZipkin(){
        List<ServiceInstance> instances=discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        if (instances==null || instances.size()<=0){
            return null;
        }
        ServiceInstance serviceInstance=loadBalance.instance(instances);
        URI uri=serviceInstance.getUri();
        String result=restTemplate.getForObject(uri+"/payment/zipkin",String.class);
        return result;
    }
}
