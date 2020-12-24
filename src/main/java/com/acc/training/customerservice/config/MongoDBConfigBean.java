package com.acc.training.customerservice.config;

import com.acc.training.customerservice.configproperties.MongoDBConfigProps;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MongoDBConfigBean {

    @Autowired
    private MongoDBConfigProps mongoDBConfigProps;

    @Bean
    public String customerCollectionName() {
        return mongoDBConfigProps.getName();
    }

}
