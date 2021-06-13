package de.bonobodev.hypermedia.repositories;


import de.bonobodev.hypermedia.dtos.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}