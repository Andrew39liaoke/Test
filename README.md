关键实现：
1. 设计数据库表
2. 首先创建公共实体类model模块
   user、content、catogery实体类
3. 创建几个模块服务
   第一个是content-service，user-service，实现自身的CRUD的MVC形式
   创建一个service-client接口，提供各个服务所需要的接口，然后content-service，user-service实现这些接口，实现一个OpenFeign的调用
4. 各个服务创建nacos的依赖
           <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-dependencies</artifactId>
            <version>2021.0.5</version>
            <type>pom</type>
            <scope>import</scope>
        </dependency>

        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-loadbalancer</artifactId>
            <version>3.1.5</version>
        </dependency>
        
        <dependency>
            <groupId>com.alibaba.cloud</groupId>
            <artifactId>spring-cloud-alibaba-sentinel-gateway</artifactId>
        </dependency>
        // 服务发现
        <dependency>
            <groupId>com.alibaba.cloud</groupId>
            <artifactId>spring-cloud-starter-alibaba-nacos-discovery</artifactId>
        </dependency>
5. 父模块引入子模块
6. 子模块引入需要调用的子模块
7. 网关实现一个路由配置，实现前缀
    gateway:
      routes:
        - id: wlx-backend-user-service
          uri: lb://wlx-backend-user-service
          predicates:
            - Path=/api/user/**
8.实现效果
![image04](https://github.com/Andrew39liaoke/Test/blob/main/image-20250625160708802.png?raw=true)
![image04](https://github.com/Andrew39liaoke/Test/blob/main/image-20250625162158359.png?raw=true)
![image04](https://github.com/Andrew39liaoke/Test/blob/main/image-20250625162238513.png?raw=true)
![image04](https://github.com/Andrew39liaoke/Test/blob/main/image-20250625162253207.png?raw=true)
![image04](https://github.com/Andrew39liaoke/Test/blob/main/image-20250625162304049.png?raw=true)
![image04](https://github.com/Andrew39liaoke/Test/blob/main/image-20250625170800463.png?raw=true)
