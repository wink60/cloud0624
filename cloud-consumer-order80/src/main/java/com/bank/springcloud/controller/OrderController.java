package com.bank.springcloud.controller;

import com.bank.springcloud.entities.CommonResult;
import com.bank.springcloud.entities.Payment;
import com.bank.springcloud.lb.LoadBalancer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.net.URI;
import java.util.List;

/**
 * @ Author     ：liul
 * @ Date       ：Created in 16:38 2020/6/25
 * @ Description：${description}
 * @ Modified By：
 * @Version: $1.00$
 */
@RestController
@Slf4j
public class OrderController {
   //单机版写死的调用生产者接口 public static final String PAYMENT_URL="http://localhost:8001";

    public static final String PAYMENT_URL="http://CLOUD-PAYMENT-SERVICE";//eureka 网页里边注册的服务 名称
    @Resource
    private RestTemplate restTemplate;
    @Resource
    private LoadBalancer loadBalancer;
    @Resource
    private DiscoveryClient discoveryClient;

    @PostMapping("/consumer/payment/create")
    public CommonResult<Payment> create(@RequestBody Payment payment){
        return restTemplate.postForObject(PAYMENT_URL+"/payment/create",payment,CommonResult.class);
    }
    /**restTemplate 返回对象为响应体中数据转化成的对象，基本上可以理解为json
     * **/
    @GetMapping("/consumer/payment/get/{id}")
    public CommonResult<Payment> getPayment(@PathVariable("id") Long id){
        return restTemplate.getForObject(PAYMENT_URL+"/payment/get/"+id,CommonResult.class);
    }
    /***
     * 返回对象为ResponseEntity 对象，包含了响应中的一些重要信息，比如响头，响应状态码，响应体等重要信息
     *
     * ***/
    @GetMapping("/consumer/payment/getForEntity/{id}")
    public CommonResult<Payment> getPayment2(@PathVariable("id") Long id){
        ResponseEntity<CommonResult> entity = restTemplate.getForEntity(PAYMENT_URL + "/payment/get/" + id, CommonResult.class);
        if(entity.getStatusCode().is2xxSuccessful()){
            return entity.getBody();
        }else{
            return new CommonResult<>(444,"操作失败错误");
        }

    }
    @GetMapping(value = "/consumer/payment/lb")
    public String getPaymentLB()
    {
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");

        if(instances == null || instances.size() <= 0)
        {
            return null;
        }

        ServiceInstance serviceInstance = loadBalancer.instances(instances);
        URI uri = serviceInstance.getUri();

        return restTemplate.getForObject(uri+"/payment/lb",String.class);

    }

}
