package com.hws;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * ClassName:EurekaApplication
 * Package:com.hws
 * Description:
 *
 * @ date:2020/10/11 16:28
 * @ author:hws
 */
@SpringBootApplication
@EnableEurekaServer //开启Eureka服务
public class EurekaApplication {

    public static void main(String[] args) {
        SpringApplication.run(EurekaApplication.class,args);
    }

}
