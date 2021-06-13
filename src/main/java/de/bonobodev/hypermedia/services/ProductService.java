package de.bonobodev.hypermedia.services;

import de.bonobodev.hypermedia.dtos.Product;
import de.bonobodev.hypermedia.exceptions.InvalidArgumentException;
import de.bonobodev.hypermedia.exceptions.NotFoundException;
import de.bonobodev.hypermedia.exceptions.OutOfStockException;

import java.util.List;

public interface ProductService {

    List<Product> getAll();

    Product get(Long id) throws NotFoundException;

    Product create(Product product) throws InvalidArgumentException;

    Product update(Product product) throws NotFoundException, InvalidArgumentException;

    void delete(Long id);

    Product buy(Long id) throws NotFoundException, OutOfStockException;
}
