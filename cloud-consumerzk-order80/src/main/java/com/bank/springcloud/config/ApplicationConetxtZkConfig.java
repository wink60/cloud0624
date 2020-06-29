package com.bank.springcloud.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @ Author     ：liul
 * @ Date       ：Created in 19:58 2020/6/26
 * @ Description：${description}
 * @ Modified By：
 * @Version: $1.00$
 */
@Configuration
public class ApplicationConetxtZkConfig {
    @Bean
    @LoadBalanced  //负载均衡 当生产者多个接口的时候
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }

}
