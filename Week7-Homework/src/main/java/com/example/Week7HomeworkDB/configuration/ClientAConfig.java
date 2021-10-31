package com.example.Week7HomeworkDB.configuration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@ConfigurationProperties(prefix = "client-a.datasource")
public class ClientAConfig {
    private String url;
    private String username;
    private String password;
}
