package com.hh3.pruebainditex.exception;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice
public final class PriceExceptionHandler {

    @ExceptionHandler(PriceNoDataFoundException.class)
    public ResponseEntity<Object> handlerUserInvalidInputException(PriceNoDataFoundException ex) {
        PriceException      priceException = new PriceException(ex.getMessage(), HttpStatus.NOT_FOUND.value());
        Map<String, Object> body           = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("status", priceException.httpStatus());
        body.put("error", "Not found");
        body.put("message", priceException.message());
        return new ResponseEntity<>(body, HttpStatusCode.valueOf(priceException.httpStatus()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {

        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDate.now());
        body.put("status", ex.getStatusCode().value());
        body.put("error", ex.getStatusCode());
        List<String> errors = ex.getBindingResult()
                                .getFieldErrors()
                                .stream()
                                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                                .collect(Collectors.toList());
        body.put("errors", errors);
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }
}