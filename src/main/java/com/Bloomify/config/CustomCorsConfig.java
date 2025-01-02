package com.Bloomify.config;


import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import java.util.List;


@Component
public class CustomCorsConfig implements CorsConfigurationSource {
    @Override
    public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowedOrigins(List.of(
                "http://localhost:8080",
                "http://127.0.0.1:8080",
                "https://admin.bloomify.life",
                "https://bloomify.life"
                ));
        config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS", "PATCH", "HEAD", "TRACE"));
        config.setAllowedHeaders(List.of("*"));
        return config;
    }
}