# cloud0624
springcloud构建代码库
  
#feign 停止更新了直接使用openfeign
     rest 的client =feign  （简洁的web Service客户端）
     feign 可以和Eureka 和ribbon 组合使用来支持负载均衡
# 使用步骤pom+注解@OpenFegin+微服务调用的接口（生产者cloud-provider-payment8001 里）
    1.pom
     <!-- openfeign -->
            <dependency>
                <groupId>org.springframework.cloud</groupId>
                <artifactId>spring-cloud-starter-openfeign</artifactId>
            </dependency>
      2.注解   
       客户端启动类上：@EnableFeignClients
       客户端service：@FeignClient(value = "CLOUD-PAYMENT-SERVICE")  
                        Eureka 应用上边的微服务名称
                        
      3.payment （提供方的接口）+ order （调用方）  
#openFeign 的超时控制（service）：cloud-provider-payment8001
       @GetMapping(value = "/payment/feign/timeout")
        public String paymentFeignTimeout()
        {
            // 业务逻辑处理正确，但是需要耗费3秒钟
            try { TimeUnit.SECONDS.sleep(3); } catch (InterruptedException e) { e.printStackTrace(); }
            return serverPort;
        }
        client（本项目）：
          @GetMapping(value = "/consumer/payment/feign/timeout")
            public String paymentFeignTimeout()
            {
                // OpenFeign客户端一般默认等待1秒钟
                return paymentFeignService.paymentFeignTimeout();
            }
 #Feign 日志打印
       1. 级别 
            NONE 默认的，不显示任何日志
            BASIC 仅记录请求方法，URL，响应状态码及执行时间
            HEADERS 除了BASIC中定义的信息之外，还有请求偶和响应的头信息
            FULL 处理HEADERS的定义信息值外，还有请求和响应的正文及元数据            
        2.FeiginConfig 里边的自身的定义信息
    
    3.配置文件开启
        logging:
          level:
            # feign日志以什么级别监控哪个接口
            com.bank.springcloud.service.PaymentFeignService: debug 
            # 监控的具体接口
现在是在父项目下

该项目是练习springCloud项目的所有代码整理

