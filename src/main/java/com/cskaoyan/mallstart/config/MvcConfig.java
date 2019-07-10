package com.cskaoyan.mallstart.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.convert.support.ConfigurableConversionService;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import javax.annotation.PostConstruct;



@Configuration
public class MvcConfig implements WebMvcConfigurer {
    @Autowired
    ConfigurableConversionService conversionService;

    @PostConstruct
    public void addConverter(){

        conversionService.addConverter(new DateConverterConfig());
        conversionService.addConverter(new Date2JsonConfig());
    }

    @Bean
    @Primary
    public ConfigurableConversionService configurableConversionService(){
        return conversionService;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        registry.addResourceHandler("/wx/storage/fetch/**")
                .addResourceLocations("classpath:/wx/storage/fetch/");
    }
}
