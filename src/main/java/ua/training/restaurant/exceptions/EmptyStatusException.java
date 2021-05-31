package ua.training.restaurant.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class EmptyStatusException extends RuntimeException {
    public EmptyStatusException(String message) {
        super(message);
    }
}
