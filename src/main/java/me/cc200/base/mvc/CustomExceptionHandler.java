package me.cc200.base.mvc;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(value = { Exception.class })
    public Rest<String> exceptionHandle(Exception e) {
        e.printStackTrace();
        return Rest.err("server exception", e.getMessage());
    }
}
