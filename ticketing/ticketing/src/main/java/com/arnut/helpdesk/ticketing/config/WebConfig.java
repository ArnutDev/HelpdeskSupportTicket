package com.arnut.helpdesk.ticketing.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")  // ให้เปิดใช้ CORS สำหรับทุก endpoint
                .allowedOrigins("http://localhost:3000")  // URL ของ React Frontend
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")  // วิธีการ HTTP ที่อนุญาต
                .allowedHeaders("*")  // อนุญาตให้ใช้ header ทุกประเภท
                .allowCredentials(true);
    }
}
