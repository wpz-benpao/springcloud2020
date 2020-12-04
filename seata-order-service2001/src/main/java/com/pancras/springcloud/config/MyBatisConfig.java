package com.pancras.springcloud.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan({"com.pancras.springcloud.dao"})
public class MyBatisConfig {
}
