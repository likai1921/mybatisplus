package org.base.mybatis;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@Slf4j
@EnableSwagger2
public class MybatisApplication {

    public static void main(String[] args) {
        log.info("start application start!");
        SpringApplication.run(MybatisApplication.class, args);
        log.info("start application success!");
        //log.error("启动成功");
    }

}
