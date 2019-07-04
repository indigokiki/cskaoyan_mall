package com.cskaoyan.config;

import org.apache.ibatis.javassist.ClassPath;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class PicConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/wx/storage/fetch/**").
                addResourceLocations("classpath:/static/wx/storage/fetch/");

    }
}
