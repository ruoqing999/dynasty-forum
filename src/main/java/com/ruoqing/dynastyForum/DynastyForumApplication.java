package com.ruoqing.dynastyForum;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan({"com.ruoqing.dynastyForum.mapper"})
public class DynastyForumApplication {

    public static void main(String[] args) {
        SpringApplication.run(DynastyForumApplication.class, args);
    }

}
