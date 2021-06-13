package de.bonobodev.hypermedia;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Main spring application class.
 */
@SpringBootApplication
public class HypermediaApplication {

    /**
     * Entry point of the application.
     */
    public static void main(String[] args) {
        SpringApplication.run(HypermediaApplication.class, args);
    }

    /**
     * Adds a cross origin resource sharing mapping for all endpoints.
     *
     * @return the CORS configurer
     */
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping(Endpoints.API + "/**");
            }
        };
    }
}
