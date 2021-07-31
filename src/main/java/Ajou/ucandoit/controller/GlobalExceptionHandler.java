package Ajou.ucandoit.controller;


import Ajou.ucandoit.util.ResFormat;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    public ResFormat handleException(Exception e) {
        return new ResFormat(false, 500L,e.getMessage() );
    }

}
