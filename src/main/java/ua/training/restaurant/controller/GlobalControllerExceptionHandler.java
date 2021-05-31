package ua.training.restaurant.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import ua.training.restaurant.exceptions.DishNotFoundException;
import ua.training.restaurant.exceptions.EmptyRequestException;
import ua.training.restaurant.exceptions.EmptyStatusException;
import ua.training.restaurant.exceptions.RequestNotFoundException;

@ControllerAdvice
@Controller
@Slf4j
public class GlobalControllerExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    public String defaultErrorHandler(Exception e) throws Exception {
        if (AnnotationUtils.findAnnotation(e.getClass(), ResponseStatus.class) != null)
            throw e;
        log.error("exception occurred during application execution: ", e);
        return "redirect:/menu";
    }

    @ExceptionHandler({EmptyStatusException.class, EmptyRequestException.class,
            RequestNotFoundException.class, DishNotFoundException.class})
    public String handleEmptyStatusException(Exception e, Model model) {
        model.addAttribute("errorMessage", e.getMessage());
        return "error";
    }

}
