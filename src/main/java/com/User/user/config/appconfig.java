package com.User.user.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
@Configuration
public class appconfig {

    @Bean
    public ModelMapper getmodel(){
        return new ModelMapper();
    }
}
