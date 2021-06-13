package de.bonobodev.hypermedia.controllers;

import de.bonobodev.hypermedia.Endpoints;
import lombok.AllArgsConstructor;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@AllArgsConstructor()
public class RootController {

    @GetMapping(Endpoints.API)
    public ResponseEntity<?> endpoint() {
        return ResponseEntity.ok(RepresentationModel.of(null, List.of(
                linkTo(methodOn(RootController.class).endpoint()).withSelfRel(),
                linkTo(methodOn(UserController.class).endpoint()).withRel("user"),
                linkTo(methodOn(ProductController.class).all()).withRel("products"))));
    }


}
