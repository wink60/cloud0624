# cloud0624
    springcloud构建代码库

#gateway 网关 -spring的
    所有的请求的一道前门，必须经过这道门才能请求的数据
    对APi 请求进行过滤，熔断，限流，降级
    达到负载均衡的过滤器（filter/MyLogGateWayFilter）
    三大核心组件：路由，断言，过滤器   

# 启动 三个 cloud-provider-payment8001，
       cloud-eureka-server7001，
       cloud-gateway-gateway9527   
#功能3利用微服务名进行路由（根据需求来决定是否要用）
         1.这个是cloud-provider-payment8001配置yml文件里边 修改localhost：8080 的显示为： cloud-payment-service
        
            spring:
              application:
                name: cloud-payment-service  #服务提供者以别名的形式把服务注册到eureka注册中心
 
    
      
      2. 自己的   cloud-gateway-gateway9527   的配置文件  
    cloud:
        gateway:
          discovery:
            locator:
              enabled: true #开启从注册中心动态创建路由的功能，利用微服务名进行路由       
     routes:
            - id: payment_routh #payment_route    #路由的ID，没有固定规则但要求唯一，建议配合服务名
              #uri: http://localhost:8001          #匹配后提供服务的路由地址
              uri: lb://cloud-payment-service #匹配后提供服务的路由地址CLOUD-PAYMENT-SERVICE
              predicates:
                - Path=/payment/get/**         # 断言，路径相匹配的进行路由 判断8001上是否有匹配路径
  
  url：localhost：9527/payment/get/1
#使用 pom+@注解引用
    1. <dependency>
                  <groupId>org.springframework.cloud</groupId>
                  <artifactId>spring-cloud-starter-gateway</artifactId>
              </dependency>
    2.注解 +详细application.yml文件
    
    3. cloud-provider-payment8001 里的两个接口在gateway的yml 文件配置两个路由     
     
        routes:
                - id: payment_routh #payment_route    #路由的ID，没有固定规则但要求唯一，建议配合服务名
                  uri: http://localhost:8001          #匹配后提供服务的路由地址
                  #uri: lb://cloud-payment-service #匹配后提供服务的路由地址CLOUD-PAYMENT-SERVICE
                  predicates:
                    - Path=/payment/get/**         # 断言，路径相匹配的进行路由 判断8001上是否有匹配路径
        
                - id: payment_routh2 #payment_route    #路由的ID，没有固定规则但要求唯一，建议配合服务名
                   uri: http://localhost:8001          #匹配后提供服务的路由地址
                  #uri: lb://cloud-payment-service #匹配后提供服务的路由地址
                  predicates:
                    - Path=/payment/lb/**         
                    
       url：localhots:8001/payment/get/1 
       gateway:localhost:9527/payment/get/1
       
 # 不用注解和yml配置-----直接硬编码写      
        cloud-gateway-gateway9527---config\GateWayConfig.java
        localhost：9527/guonei->   跳转到 http://news.baidu.com/guonei   百度国内新闻页面       
现在是在父项目下

该项目是练习springCloud项目的所有代码整理

