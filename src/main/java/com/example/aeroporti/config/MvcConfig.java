package com.example.aeroporti.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // Aggiungi questa configurazione per evitare conflitti con le risorse statiche
        registry.addResourceHandler("/**").addResourceLocations("classpath:/static/");
    }
}