package de.bonobodev.hypermedia.advices;


import de.bonobodev.hypermedia.exceptions.InvalidLoginException;
import de.bonobodev.hypermedia.exceptions.UsernameAlreadyExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Handles all exceptions for the user controller.
 */
@ControllerAdvice
public class UserAdvice {

    @ExceptionHandler(UsernameAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    ResponseEntity<?> usernameAlreadyExistsHandler(UsernameAlreadyExistsException exception) {
        return null;
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    ResponseEntity<?> usernameNotFoundHandler(UsernameNotFoundException exception) {
        return null;
    }

    @ExceptionHandler(InvalidLoginException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    ResponseEntity<?> invalidLoginHandler(InvalidLoginException exception) {
        return null;
    }
}
