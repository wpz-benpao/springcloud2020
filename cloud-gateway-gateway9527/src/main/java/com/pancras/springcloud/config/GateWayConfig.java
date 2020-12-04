package com.pancras.springcloud.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * gateway网关路由有两种配置，一种是Bean配置，一种是yml文件配置
 */
@Configuration
public class GateWayConfig {
    @Bean
    RouteLocator customRouteLocator(RouteLocatorBuilder routeLocatorBuilder){
        RouteLocatorBuilder.Builder routes=routeLocatorBuilder.routes();
        routes.route("gateway_route1",
                r->r.path("/guoji").
                        uri("http://news.baidu.com/guoji")).build();
        routes.route("gateway_route2",
                r->r.path("/game").
                        uri("http://news.baidu.com/game")).build();
        return routes.build();
    }
}
