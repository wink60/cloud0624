package com.bank.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @ Author     ：liul
 * @ Date       ：Created in 23:13 2020/6/24
 * @ Description：${建成一个主启动类}
 * @ Modified By：
 * @Version: $1.00$
 */
@SpringBootApplication
@EnableEurekaClient
@EnableDiscoveryClient
public class PaymentMain8001 {
    public static void main(String [] args){
        SpringApplication.run(PaymentMain8001.class,args);
    }
}
