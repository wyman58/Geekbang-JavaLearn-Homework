package com.example.Week7HomeworkDB.configuration;

import com.example.Week7HomeworkDB.datasource.ClientDataSourceRouter;
import com.example.Week7HomeworkDB.datasource.ClientDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class RoutingConfiguration {

    @Autowired
    ClientAConfig clientAConfig;

    @Autowired
    ClientBConfig clientBConfig;

//    @Bean
//    public DataSource clientDatasource(){
//        Map<Object,Object> targetDatasources = new HashMap<>();
//        DataSource clientADatasource = clientADatasource();
//        DataSource clientBDatasource = clientBDatasource();
//        targetDatasources.put(ClientDatabase.CLIENT_A, clientADatasource);
//        targetDatasources.put(ClientDatabase.CLIENT_B, clientBDatasource);
//        ClientDataSourceRouter clientDataSourceRouter = new ClientDataSourceRouter();
//        clientDataSourceRouter.setTargetDataSources(targetDatasources);
//        clientDataSourceRouter.setDefaultTargetDataSource(clientADatasource);
//        return clientDataSourceRouter;
//    }

    private DataSource clientADatasource(){
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl(clientAConfig.getUrl());
        dataSource.setUsername(clientAConfig.getUsername());
        dataSource.setPassword(clientAConfig.getPassword());
        return dataSource;
    }

    private DataSource clientBDatasource(){
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl(clientBConfig.getUrl());
        dataSource.setUsername(clientBConfig.getUsername());
        dataSource.setPassword(clientBConfig.getPassword());
        return dataSource;
    }
}
