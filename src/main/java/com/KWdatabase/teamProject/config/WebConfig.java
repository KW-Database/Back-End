package com.KWdatabase.teamProject.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:3000",
                        "http://localhost:8080",
                        "http://localhost:8081"
                        ).allowedMethods("OPTIONS", "GET", "POST", "PUT", "DELETE");; // 허용할 출처
    }
}