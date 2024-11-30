package com.jbk.exceptions;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import javax.servlet.http.HttpServletRequest;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

@RestControllerAdvice
public class GlobalExceptionHandler extends Throwable {

    // Handle validation errors
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public HashMap<String, String> handleInvalidInput(MethodArgumentNotValidException ex) {
        HashMap<String, String> errorMap = new HashMap<>();

        List<FieldError> fieldErrors = ex.getFieldErrors();
        for (FieldError fieldError : fieldErrors) {
            errorMap.put(fieldError.getField(), fieldError.getDefaultMessage());
        }
        return errorMap;
    }

    // Handle resource not found exceptions
    @ExceptionHandler(ResourceNotFoundException.class)
    public ExceptionResponse handleResourceNotFoundException(ResourceNotFoundException ex, HttpServletRequest request) {
        ExceptionResponse response = new ExceptionResponse();
        response.setMessage(ex.getMessage());
        response.setPath(request.getRequestURI());
        response.setTimestamp(new Timestamp(System.currentTimeMillis()));
        return response;
    }










}
