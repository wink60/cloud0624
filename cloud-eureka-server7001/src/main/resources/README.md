# cloud0624
springcloud构建代码库 ③ 三

Eureka 相当于 开发商和买房用户之间的中介

#此次默认eureka 端口7001 ，7002开头
    开发商生产出来---用户通过中介进行调用使用
    service        client
# 启动后查看url地址 localhost:7001    ---查看注册的服务信息application 下


# 把cloud-provider-payment8001 和 cloud-consumer-order80 注册进入eurake

#引入pom 文件的jar包+注解
     1. <!--eureka-server-->
                <dependency>
                    <groupId>org.springframework.cloud</groupId>
                    <artifactId>spring-cloud-starter-netflix-eureka-server</artifactId>
                </dependency>
                
      2.@EnableEurekaServer  
#先启动eureka 再启动service 和client

#Eureka 是服务治理的
     传统的n 多个消费者和n 多个 服务者 调用比较复杂
     Eureka 就是解决这个问题  

#actuator 检查eureka 服务是否活着
    #http://localhost:8001/actuator/health
#discovery 微服务对外提供的服务信息
    http://localhost:8002/payment/discovery
    
# Eureka 有自我保护机制，当网络断了不会立马清除 微服务
    会保存 一定时间后仍旧不存活的 才清理    
    server:
        #关闭eureka 自我保护机制，保证不可用服务被及时剔除
        enable-self-preservation: false #默认是true
        eviction-interval-timer-in-ms: 2000
        
        可以通过eureka 里边实例是否存在查看并验证
         
该项目是练习springCloud项目的所有代码整理

