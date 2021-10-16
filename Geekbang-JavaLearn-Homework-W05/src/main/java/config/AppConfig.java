package config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan("Service")
public class AppConfig {

    @Bean(name = "myBean")
    public MyBean myBean(){
        return new MyBean();
    }
}


