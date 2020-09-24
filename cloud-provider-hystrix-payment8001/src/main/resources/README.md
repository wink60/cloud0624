# cloud0624
springcloud构建代码库 cloud-provider-hystrix-payment8001
                消费方：cloud-consumer-feign-hystrix-order80

  
#Hystrix 服务降级/熔断/监控（保证延迟和容错）--保险丝
   复杂分布式体系结构中应用程序有数是个依赖关系，每个依赖关系在某些时候不能避免的失败
   容易造成服务雪崩，系统崩溃
   
    1 服务降级   服务器忙 --(现在忙，请稍后再试)
    
               那些情况会发出服务降级-异常/超时、线程池信号量满
    2 服务熔断   ---保险丝  （拉闸-断电-恢复） 
    
    3.服务限流   秒杀限流排队        
   
# pom+注解
    1. <!--hystrix-->
              <dependency>
                  <groupId>org.springframework.cloud</groupId>
                  <artifactId>spring-cloud-starter-netflix-hystrix</artifactId>
              </dependency>
    2.
    
#代码编程
    controller：
         @GetMapping("/payment/hystrix/timeout/{id}")
                  public String paymentInfo_TimeOut(@PathVariable("id") Integer id)
                  {
                      String result = paymentService.paymentInfo_TimeOut(id);
                      log.info("*****result: "+result);
                      return result;
                  }
                  //===此句以上是服务降级
                  //====此句以下服务熔断--测试的时候先传递一次正数，再传递9次负数，达到熔断阈值，会熔断一段时间再恢复
                  @GetMapping("/payment/circuit/{id}")
                  public String paymentCircuitBreaker(@PathVariable("id") Integer id)
                  {
                      String result = paymentService.paymentCircuitBreaker(id);
                      log.info("****result: "+result);
                      return result;
                  }               
        service：
            @HystrixCommand(fallbackMethod = "paymentInfo_TimeOutHandler",commandProperties = {
                    @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds",value="5000")
            })
            public String paymentInfo_TimeOut(Integer id)
            {
    
            int timeOut=3000;
            //int age = 10/0;
            try { TimeUnit.MILLISECONDS.sleep(timeOut); } catch (InterruptedException e) { e.printStackTrace(); }
            return "线程池:  "+Thread.currentThread().getName()+" id:  "+id+"\t"+"O(∩_∩)O哈哈~"+"  耗时(秒): "+timeOut;
        }
        public String paymentInfo_TimeOutHandler(Integer id)
        {
            return "线程池:  "+Thread.currentThread().getName()+"  8001系统繁忙或者运行报错，请稍后再试,id:  "+id+"\t"+"o(╥﹏╥)o";
        }
        //===本句上边是服务降级fallback 有兜底函数方法
        //=====以下是服务熔断 下边的参数有很多可以参考HystrixCommandproperties 类搜索打开源码
        @HystrixCommand(fallbackMethod = "paymentCircuitBreaker_fallback",commandProperties = {
                @HystrixProperty(name = "circuitBreaker.enabled",value = "true"),// 是否开启断路器
                @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold",value = "10"),// 请求次数
                @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds",value = "10000"), // 时间窗口期
                @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage",value = "60"),// 失败率达到多少后跳闸
                //上边的意思是在10s内10次请求失败率达到60% 就会断路调用上边的paymentCircuitBreaker_fallback 方法
        })
        public String paymentCircuitBreaker(@PathVariable("id") Integer id)
        {
            if(id < 0)
            {
                throw new RuntimeException("******id 不能负数");//如果出事调用服务降级的下边paymentCircuitBreaker_fallback兜底方法
            }
            String serialNumber = IdUtil.simpleUUID();//UUID.randomUUID();等价的
    
            return Thread.currentThread().getName()+"\t"+"调用成功，流水号: " + serialNumber;
        }
        public String paymentCircuitBreaker_fallback(@PathVariable("id") Integer id)
        {
            return "id 不能负数，请稍后再试，/(ㄒoㄒ)/~~   id: " +id;
        }
     

该项目是练习springCloud项目的所有代码整理

