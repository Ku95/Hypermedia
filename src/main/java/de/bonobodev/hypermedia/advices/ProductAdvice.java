package de.bonobodev.hypermedia.advices;

import de.bonobodev.hypermedia.exceptions.InvalidArgumentException;
import de.bonobodev.hypermedia.exceptions.NotFoundException;
import de.bonobodev.hypermedia.exceptions.OutOfStockException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Handles all exceptions for the products controller.
 */
@ControllerAdvice
public class ProductAdvice {

    @ResponseBody
    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String productNotFoundHandler(NotFoundException exception) {
        return exception.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(InvalidArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    String invalidArgumentHandler(InvalidArgumentException exception) {
        return exception.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(OutOfStockException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    String outOfStockHandler(OutOfStockException exception) {
        return exception.getMessage();
    }
}
