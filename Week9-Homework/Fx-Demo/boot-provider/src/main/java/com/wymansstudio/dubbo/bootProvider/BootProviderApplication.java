package com.wymansstudio.dubbo.bootProvider;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableDubbo
@SpringBootApplication
@MapperScan("com.wymansstudio.dubbo.boot.api.fxAccount.mapper")
public class BootProviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(BootProviderApplication.class, args);
    }

}
