# cloud0624
springcloud构建代码库 ③

cusmer 80
#消费方 

#注册到eureka 需要pom + 注解 引用
     Ⅰ.<!--eureka-client  对应着eureka 里边的server 是eureka的两个组件-->
                   <dependency>
                       <groupId>org.springframework.cloud</groupId>
                       <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
                   </dependency>
                 
     2.@EnableDiscoveryClient  

# 访问路径：localhost:80/consumer/payment/get/1

#配置文件 优化ip eureka 浏览器中实例名称显示
      instance:
         instance-id: payment8001 #给http://eureka7001.com:7001/ 这个目录中的实例展示状态cloud-pr0vider-payment8001进行优化，结合pom文件图形化
         prefer-ip-address: true  #和上边类似优化显示ip      


 # 配置文件 优化ip eureka 中显示
     instance:
        instance-id: payment8001 #给http://eureka7001.com:7001/ 这个目录中的实例展示状态cloud-pr0vider-payment8001进行优化，结合pom文件图形化
        prefer-ip-address: true  #和上边类似两者组合优化显示ip
                   
  #http://localhost:8001/actuator/health 检查 eureka 存活状态
 
 # 暴露微服务清单 payment8001 上
      1.@注解标签+ DiscoveryClient 
      2. 浏览器查看
       http://localhost:8001/payment/discovery
       展示在Eureka 中注册过的所有服务列表信息
      3.主启动上加注解 @EnableDiscoveryClient 
# Eureka 可继承Ribbon 详细可参考 
    myrule 文件包 和
    config 还有
    controler 里的/consumer/payment/getForEntity/{id} 
  
该项目是练习springCloud项目的所有代码整理

