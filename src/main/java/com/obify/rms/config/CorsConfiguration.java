package com.obify.rms.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfiguration {
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOriginPatterns("*") //here
                        .allowedMethods("PUT", "DELETE", "POST", "GET", "PATCH")
                        .allowedHeaders("CustomAuth", "Authorization", "X-API-KEY", "Origin", "Access-Control-Allow-Origin", "Content-Type",
                                "Accept", "Origin, Accept", "X-Requested-With",
                                "Access-Control-Request-Method", "Access-Control-Request-Headers")
                        .exposedHeaders("CustomAuth", "Origin", "Content-Type", "Accept", "Authorization", "X-API-KEY",
                                "Access-Control-Allow-Origin", "Access-Control-Allow-Origin", "Access-Control-Allow-Credentials");
                        //.allowCredentials(true);
            }
        };
    }
}
