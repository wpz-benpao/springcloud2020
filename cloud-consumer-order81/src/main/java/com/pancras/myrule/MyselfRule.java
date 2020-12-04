package com.pancras.myrule;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 此配置类不能在ComponentScan注解扫描所在包和所在子包下
 */
@Configuration
public class MyselfRule {

    @Bean
    public IRule iRule(){
        return new RandomRule();//将负载均衡的策略改为随机，默认为轮询
    }
}
