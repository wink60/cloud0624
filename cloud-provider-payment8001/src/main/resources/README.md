# cloud-provider-payment8001 ①
 #1.这是新建的第一个子模块
 订单模块 微服务提供者
 
  #2.启动后调用路径
    localhost:8001/payment/get/1
    浏览器显示数据查询结果：
    自身直接调用
        
 #3.详细可以看application.yml 配置文件
 了解后续的单机版和集群版
 
 #4.注册进入eureka需要pom +注解
        一.<!--eureka-client  对应着eureka 里边的server 是eureka的两个组件-->
            <dependency>
                <groupId>org.springframework.cloud</groupId>
                <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
            </dependency>
        二.@EnableDiscoveryClient     
            
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
              
该项目是练习springCloud项目的所有代码整理

