package de.bonobodev.hypermedia;

import de.bonobodev.hypermedia.dtos.Product;
import de.bonobodev.hypermedia.repositories.ProductRepository;
import de.bonobodev.hypermedia.services.UserService;
import lombok.extern.java.Log;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Log
public class LoadDatabase {

    @Bean
    CommandLineRunner initProducts(ProductRepository repository) {
        return args -> {
            log.info("Preloading " + repository.save(new Product("Apple", 3, 2.5f)));
            log.info("Preloading " + repository.save(new Product("Banana", 5, 0.8f)));
        };
    }

    @Bean
    CommandLineRunner initUsers(UserService service) {
        return args -> {
            log.info("Preloading " + service.register("admin", "admin"));
        };
    }
}
