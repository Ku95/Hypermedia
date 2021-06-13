package de.bonobodev.hypermedia.assemblers;

import de.bonobodev.hypermedia.controllers.UserController;
import de.bonobodev.hypermedia.dtos.User;
import lombok.SneakyThrows;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class UserModelAssembler implements RepresentationModelAssembler<User, EntityModel<User>> {
    @SneakyThrows
    @Override
    public EntityModel<User> toModel(User user) {
        EntityModel<User> userModel = EntityModel.of(user,
                linkTo(methodOn(UserController.class).current(user)).withSelfRel());

        if (user.isValid()) {
            userModel.add(linkTo(methodOn(UserController.class).logout(null)).withRel("logout"));
        } else {
            userModel.add(linkTo(methodOn(UserController.class).login(null, null)).withRel("login"),
                    linkTo(methodOn(UserController.class).register(null, null)).withRel("register"));
        }

        return userModel;
    }
}
