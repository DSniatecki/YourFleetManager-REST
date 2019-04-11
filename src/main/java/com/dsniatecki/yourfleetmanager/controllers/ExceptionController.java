package com.dsniatecki.yourfleetmanager.controllers;

import com.dsniatecki.yourfleetmanager.exceptions.ExceptionRestResponse;
import com.dsniatecki.yourfleetmanager.exceptions.ResourceNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@Slf4j
@RestController
@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Object> handleResourceNotFoundException(
            ResourceNotFoundException exception, WebRequest webRequest){

        log.info("ResourceNotFoundException has been properly handled !");
        return new ResponseEntity<>(generateRestExcResponse(exception, webRequest), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(EmptyResultDataAccessException.class)
    public ResponseEntity<Object> handleEmptyResultDataAccessException(
            EmptyResultDataAccessException exception, WebRequest webRequest){

        log.info("EmptyResultDataAccessException has been properly handled !");
        return new ResponseEntity<>(generateRestExcResponse(exception, webRequest), HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(NumberFormatException.class)
    public ResponseEntity<Object> handleNumberFormatException(
            NumberFormatException exception, WebRequest webRequest){

        log.info("NumberFormatException has been properly handled !");
        return new ResponseEntity<>(generateRestExcResponse(exception, webRequest), HttpStatus.BAD_REQUEST);
    }


    private ExceptionRestResponse generateRestExcResponse(Exception exception, WebRequest webRequest){
        return ExceptionRestResponse.builder()
                .timestamp(new Date())
                .message(exception.getMessage())
                .details(webRequest.getDescription(false))
                .build();
    }

}
