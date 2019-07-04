package com.cskaoyan.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

@Configuration
public class FileConfig {

    @Bean("multipartResolver")
    public CommonsMultipartResolver commonsMultipartResolver(){
        CommonsMultipartResolver CommonsMultipartResolver = new CommonsMultipartResolver();
        return CommonsMultipartResolver;
    }
}
