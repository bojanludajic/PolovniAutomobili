package com.example.demo.exception;


import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NoPartsAvailableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleNoPartsAvailable(Exception ex, Model m) {
        m.addAttribute("message", ex.getMessage());

        return "error";
    }

    @ExceptionHandler(TooManyRequestsException.class)
    @ResponseStatus(HttpStatus.TOO_MANY_REQUESTS)
    public String handleTooManyRequests(Exception ex, Model m) {
        m.addAttribute("message", ex.getMessage());

        return "error";
    }

    @ExceptionHandler(ServiceNotAvailableException.class)
    @ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
    public String handleServiceNotAvailable(Exception ex, Model m) {
        m.addAttribute("message", ex.getMessage());

        return "error";
    }
}
