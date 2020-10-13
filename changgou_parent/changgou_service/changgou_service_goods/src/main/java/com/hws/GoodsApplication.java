package com.hws;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * ClassName:GoodsApplication
 * Package:com.hws
 * Description:
 *
 * @ date:2020/10/12 15:33
 * @ author:hws
 */
@SpringBootApplication
@EnableEurekaClient //开启Eureka客户端
@MapperScan(basePackages = {"com.hws.dao"}) //@MapperScan是tk.mybatis.spring.annotation包下的
public class GoodsApplication {
    public static void main(String[] args) {
        SpringApplication.run(GoodsApplication.class,args);
    }
}
