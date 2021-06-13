package de.bonobodev.hypermedia.controllers;

import de.bonobodev.hypermedia.Endpoints;
import de.bonobodev.hypermedia.assemblers.ProductModelAssembler;
import de.bonobodev.hypermedia.dtos.Product;
import de.bonobodev.hypermedia.exceptions.InvalidArgumentException;
import de.bonobodev.hypermedia.exceptions.NotFoundException;
import de.bonobodev.hypermedia.exceptions.OutOfStockException;
import de.bonobodev.hypermedia.services.ProductService;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@AllArgsConstructor()
public class ProductController {

    @NonNull
    private final ProductService productService;
    @NonNull
    private final ProductModelAssembler assembler;

    @GetMapping(Endpoints.PRODUCTS)
    public ResponseEntity<?> all() {
        List<EntityModel<Product>> products = productService.getAll().stream()
                .map(assembler::toModel).collect(Collectors.toList());

        return ResponseEntity.ok(CollectionModel.of(products, linkTo(methodOn(ProductController.class).all()).withSelfRel()));
    }

    @PostMapping(Endpoints.PRODUCTS)
    public ResponseEntity<?> createProduct(@RequestBody Product product) throws InvalidArgumentException {
        EntityModel<Product> entityModel = assembler.toModel(productService.create(product));

        return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(entityModel);
    }

    @GetMapping(Endpoints.PRODUCT)
    public ResponseEntity<?> one(@PathVariable Long id) throws NotFoundException {
        return ResponseEntity.ok(assembler.toModel(productService.get(id)));
    }

    @PatchMapping(Endpoints.PRODUCT)
    public ResponseEntity<?> updateProduct(@PathVariable Long id, @RequestBody Product product) throws InvalidArgumentException, NotFoundException {
        product.setId(id);

        EntityModel<Product> entityModel = assembler.toModel(productService.update(product));

        return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(entityModel);
    }

    @DeleteMapping(Endpoints.PRODUCT)
    public ResponseEntity<?> deleteProduct(@PathVariable Long id) {
        productService.delete(id);

        return ResponseEntity.noContent().build();
    }

    @GetMapping(Endpoints.BUY)
    public ResponseEntity<?> buy(@PathVariable long id) throws OutOfStockException, NotFoundException {
        return ResponseEntity.ok(assembler.toModel(productService.buy(id)));
    }
}