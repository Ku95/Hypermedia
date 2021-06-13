package de.bonobodev.hypermedia.services;

import de.bonobodev.hypermedia.dtos.Product;
import de.bonobodev.hypermedia.exceptions.InvalidArgumentException;
import de.bonobodev.hypermedia.exceptions.NotFoundException;
import de.bonobodev.hypermedia.exceptions.OutOfStockException;
import de.bonobodev.hypermedia.repositories.ProductRepository;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductServiceImplementation implements ProductService {

    @NonNull
    private final ProductRepository repository;

    @Override
    public List<Product> getAll() {
        return repository.findAll();
    }

    @Override
    public Product get(Long id) throws NotFoundException {
        return repository.findById(id).orElseThrow(NotFoundException::new);
    }

    @Override
    public Product create(Product product) throws InvalidArgumentException {
        if (!isValidCreation(product)) {
            throw new InvalidArgumentException();
        }

        product.setId(null);

        return repository.save(product);
    }

    @Override
    public Product update(Product product) throws NotFoundException, InvalidArgumentException {
        if (!isValidUpdate(product)) {
            throw new InvalidArgumentException();
        }

        return repository.findById(product.getId())
                .map(existingProduct -> { // update changed fields
                    if (product.getName() != null) {
                        existingProduct.setName(product.getName());
                    }
                    if (product.getAmountInStock() != null) {
                        existingProduct.setAmountInStock(product.getAmountInStock());
                    }
                    if (product.getPrice() != null) {
                        existingProduct.setPrice(product.getPrice());
                    }
                    if (product.getAvailable() != null) {
                        existingProduct.setAvailable(product.getAvailable());
                    }

                    return repository.save(existingProduct);
                })
                .orElseThrow(NotFoundException::new);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Product buy(Long id) throws NotFoundException, OutOfStockException {
        Product product = repository.findById(id).orElseThrow(NotFoundException::new);

        if (!product.getAvailable() || product.getAmountInStock() == 0) {
            throw new OutOfStockException();
        }

        product.setAmountInStock(product.getAmountInStock() - 1);

        if (product.getAmountInStock() == 0) {
            product.setAvailable(false);
        }

        return repository.save(product);
    }

    private boolean isValidCreation(Product product) {
        return product.getName() != null &&
                product.getAmountInStock() != null &&
                product.getPrice() != null &&
                isValidUpdate(product);
    }

    private boolean isValidUpdate(Product product) {
        return (product.getName() == null || !product.getName().equals("")) &&
                (product.getAmountInStock() == null || product.getAmountInStock() >= 0) &&
                (product.getPrice() == null || product.getPrice() >= 0);
    }
}
