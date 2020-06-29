package com.bank.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @ Author     ：liul
 * @ Date       ：Created in 19:56 2020/6/26
 * @ Description：${description}
 * @ Modified By：
 * @Version: $1.00$
 */
@SpringBootApplication
@EnableDiscoveryClient
public class applicationZkOrderMain80 {
    public static void main(String[] args) {
        SpringApplication.run(applicationZkOrderMain80.class,args);
    }
}
