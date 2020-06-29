package com.bank.myrule;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import com.netflix.loadbalancer.RetryRule;
import org.springframework.cloud.netflix.ribbon.RibbonClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ Author     ：liul
 * @ Date       ：Created in 17:19 2020/6/27
 * @ Description：${自定义Ribbon 客户端负载均衡算法为随机算法如下
 *  1.本类不能和主启动在同一个包下
 *  2.需要在原先的主启动类下指定本方法为负载均衡算法 @@RibbonClient(name = "CLOUD-PAYMENT-SERVICE",configuration=MySelfRule.class)
 * }
 * @ Modified By：
 * @Version: $1.00$
 */
@Configuration
public class MySelfRule {
    @Bean
    public IRule myRule(){
        System.out.println("调用随机算法");
        return new RandomRule();//定义为随机算法
    }
}
