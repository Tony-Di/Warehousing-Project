package org.example.springbootpractice2024;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    // redirect webs for different origins server side is on port 8080 front end is on port 8081
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // Applies CORS to all routes
                .allowedOrigins("http://localhost:8081") // Allow this origin to access API
                .allowedMethods("GET", "POST", "PUT", "DELETE") // Methods allowed
                .allowedHeaders("*") // All headers allowed
                .allowCredentials(true); // Allow credentials for authentication
    }
}
