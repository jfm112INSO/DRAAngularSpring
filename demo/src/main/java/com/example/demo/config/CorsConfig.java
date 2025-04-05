package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class CorsConfig {

    @Bean
    public CorsFilter corsFilter() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true); // Permite enviar cookies y auth
        config.addAllowedOriginPattern("*"); // Permite todas las URLs
        config.addAllowedHeader("*"); // Permite todos los headers
        config.addAllowedMethod("*"); // Permite todos los métodos (GET, POST, etc.)

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config); // Aplica a todas las rutas

        return new CorsFilter(source);
    }
}
