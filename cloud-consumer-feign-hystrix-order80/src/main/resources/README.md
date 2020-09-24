# cloud0624
springcloud构建代码库 cloud-consumer-feign-hystrix-order80
                service：cloud-provider-hystrix-payment8001

# 消费方

    1.注解
     @FeignClient(value = "CLOUD-PROVIDER-HYSTRIX-PAYMENT" ,fallback = PaymentFallbackService.class)
    2.pom
     <dependency>
                <groupId>com.netflix.hystrix</groupId>
                <artifactId>hystrix-javanica</artifactId>
                <version>RELEASE</version>
                <scope>compile</scope>
            </dependency>
    
 
# 代码 消费端 自己进行降级处理也可以服务端进行降级或者两端都做降级
    cloud-provider-hystrix-payment8001
   #controller
                 @GetMapping("/consumer/payment/hystrix/ok/{id}")
                    public String paymentInfo_OK(@PathVariable("id") Integer id)
                    {
                        String result = paymentHystrixService.paymentInfo_OK(id);
                        return result;
                    }
            
            @GetMapping("/consumer/payment/hystrix/timeout/{id}")
            @HystrixCommand(fallbackMethod = "paymentTimeOutFallbackMethod",commandProperties = {
                    @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds",value="1500")
            })
            //@HystrixCommand 上边的注释掉用这个表示没有指定就用全局默认的
            public String paymentInfo_TimeOut(@PathVariable("id") Integer id)
            {
                int age = 10/0;
                String result = paymentHystrixService.paymentInfo_TimeOut(id);
                return result;
            }
            public String paymentTimeOutFallbackMethod(@PathVariable("id") Integer id)
            {
                return "我是消费者80,对方支付系统繁忙请10秒钟后再试或者自己运行出错请检查自己,o(╥﹏╥)o";
            }
        
            // 下面是全局fallback方法---通用的（每个方法一个定制的，100个方法100个定制有点傻逼）
            public String payment_Global_FallbackMethod()
            {
                return "Global异常处理信息，请稍后再试，/(ㄒoㄒ)/~~";
            }

现在是在父项目下

该项目是练习springCloud项目的所有代码整理

