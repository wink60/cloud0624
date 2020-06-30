package com.bank.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

/**
 * @ Author     ：liul
 * @ Date       ：Created in 20:10 2020/6/29
 * @ Description：${description}
 * @ Modified By：
 * @Version: $1.00$
 */
@SpringBootApplication
@EnableHystrixDashboard
public class HystrixDashboardMain9001 {
    public static void main(String[] args) {
        SpringApplication.run(HystrixDashboardMain9001.class, args);

    }
}
