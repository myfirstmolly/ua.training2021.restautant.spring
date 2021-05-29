package ua.training.restaurant.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
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

    @ExceptionHandler(EmptyRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleEmptyRequestException(Model model) {
        model.addAttribute("errorMessage", "order is empty");
        model.addAttribute("status", "BAD REQUEST");
        return "error";
    }

    @ExceptionHandler(EmptyStatusException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleEmptyStatusException(Model model) {
        model.addAttribute("errorMessage", "status is empty");
        model.addAttribute("status", "BAD REQUEST");
        return "error";
    }

    @ExceptionHandler(RequestNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleRequestNotFoundException(Model model) {
        model.addAttribute("errorMessage", "this order is not found");
        model.addAttribute("status", "NOT FOUND");
        return "error";
    }

}
