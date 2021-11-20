package com.example.Week8Homework.configuration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@ConfigurationProperties(prefix = "client-b.datasource")
public class ClientBConfig {
    private String url;
    private String username;
    private String password;
}
