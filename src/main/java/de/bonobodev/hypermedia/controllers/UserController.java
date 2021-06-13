package de.bonobodev.hypermedia.controllers;

import de.bonobodev.hypermedia.Endpoints;
import de.bonobodev.hypermedia.assemblers.UserModelAssembler;
import de.bonobodev.hypermedia.dtos.User;
import de.bonobodev.hypermedia.exceptions.InvalidLoginException;
import de.bonobodev.hypermedia.exceptions.UsernameAlreadyExistsException;
import de.bonobodev.hypermedia.services.UserService;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor()
public class UserController {

    @NonNull
    private final UserService userService;
    @NonNull
    private final UserModelAssembler assembler;

    @GetMapping(Endpoints.USER)
    public ResponseEntity<?> endpoint() {
        return ResponseEntity.ok(assembler.toModel(new User()));
    }

    @CrossOrigin
    @GetMapping(Endpoints.CURRENT)
    public ResponseEntity<?> current(@AuthenticationPrincipal User user) {
        return ResponseEntity.ok(assembler.toModel(user));
    }

    @PostMapping(Endpoints.REGISTER)
    public ResponseEntity<?> register(@RequestParam("username") String username,
                                      @RequestParam("password") String password) throws UsernameAlreadyExistsException {
        return ResponseEntity.ok(assembler.toModel(userService.register(username, password)));
    }

    @PostMapping(Endpoints.LOGIN)
    public ResponseEntity<?> login(@RequestParam("username") String username,
                                   @RequestParam("password") String password) throws InvalidLoginException, UsernameNotFoundException {
        return ResponseEntity.ok(assembler.toModel(userService.login(username, password)));
    }

    @GetMapping(Endpoints.LOGOUT)
    public ResponseEntity<?> logout(@AuthenticationPrincipal User user) {
        userService.logout(user);
        return ResponseEntity.noContent().build();
    }
}