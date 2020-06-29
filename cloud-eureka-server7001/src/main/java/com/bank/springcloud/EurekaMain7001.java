package com.bank.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @ Author     ：liul
 * @ Date       ：Created in 20:32 2020/6/25
 * @ Description：${Eureka 注册中心：两个模块注册模块（server和client）}
 * @ Modified By：
 * @Version: $1.00$
 */
@SpringBootApplication
@EnableEurekaServer
public class EurekaMain7001 {
    public static void main(String[] args) {
        SpringApplication.run(EurekaMain7001.class,args);
    }
}
