package de.bonobodev.hypermedia.assemblers;

import de.bonobodev.hypermedia.controllers.ProductController;
import de.bonobodev.hypermedia.dtos.Product;
import lombok.SneakyThrows;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

/**
 * Assembles all hypermedia links for a product.
 */
@Component
public class ProductModelAssembler implements RepresentationModelAssembler<Product, EntityModel<Product>> {

    @SneakyThrows
    @Override
    public EntityModel<Product> toModel(Product product) {
        EntityModel<Product> productModel = EntityModel.of(product,
                linkTo(methodOn(ProductController.class).one(product.getId())).withSelfRel(),
                linkTo(methodOn(ProductController.class).all()).withRel("products"));

        if (product.getAvailable() && product.getAmountInStock() > 0) {
            productModel.add(linkTo(methodOn(ProductController.class).buy(product.getId())).withRel("buy"));
        }

        return productModel;
    }
}
