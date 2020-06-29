package com.bank.springcloud;

import com.bank.myrule.MySelfRule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.netflix.ribbon.RibbonClients;

/**
 * @ Author     ：liul
 * @ Date       ：Created in 16:24 2020/6/25
 * @ Description：${description}
 * @ Modified By：
 * @Version: $1.00$
 */

@SpringBootApplication
@EnableEurekaClient
@RibbonClient(name = "CLOUD-PAYMENT-SERVICE",configuration= MySelfRule.class)
public class OrderMain80{
    public static void main(String[] args) {
        SpringApplication.run(OrderMain80.class,args);
    }
}
