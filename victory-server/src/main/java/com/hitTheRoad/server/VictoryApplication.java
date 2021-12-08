package com.hitTheRoad.server;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling  //开启定时任务
@MapperScan("com.hitTheRoad.server.mapper")
public class VictoryApplication {
    public static void main(String[] args) {
        SpringApplication.run(VictoryApplication.class,args);
    }
}
