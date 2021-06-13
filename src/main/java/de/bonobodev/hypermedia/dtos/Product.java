package de.bonobodev.hypermedia.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
@NoArgsConstructor
public class Product {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private Integer amountInStock;
    private Float price;
    private Boolean available;

    public Product(String name, Integer amountInStock, Float price) {
        id = null;
        this.name = name;
        this.amountInStock = amountInStock;
        this.price = price;
        this.available = amountInStock > 0;
    }
}
