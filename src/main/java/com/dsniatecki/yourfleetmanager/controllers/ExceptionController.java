package com.dsniatecki.yourfleetmanager.controllers;

import com.dsniatecki.yourfleetmanager.exceptions.ExceptionRestResponse;
import com.dsniatecki.yourfleetmanager.exceptions.ResourceNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.mapping.PropertyReferenceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@Slf4j
@RestController
@ControllerAdvice
public class ExceptionController extends ResponseEntityExceptionHandler {
    
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Object> handleResourceNotFoundException(
            ResourceNotFoundException exception, WebRequest webRequest){

        log.info(generateHandledExceptionMessage(exception.getClass().getSimpleName()));
        return new ResponseEntity<>(generateRestExcResponse(exception, webRequest), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(EmptyResultDataAccessException.class)
    public ResponseEntity<Object> handleEmptyResultDataAccessException(
            EmptyResultDataAccessException exception, WebRequest webRequest){

        log.info(generateHandledExceptionMessage(exception.getClass().getSimpleName()));
        return new ResponseEntity<>(generateRestExcResponse(exception, webRequest), HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(NumberFormatException.class)
    public ResponseEntity<Object> handleNumberFormatException(
            NumberFormatException exception, WebRequest webRequest){

        log.info(generateHandledExceptionMessage(exception.getClass().getSimpleName()));
        return new ResponseEntity<>(generateRestExcResponse(exception, webRequest), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({IllegalArgumentException.class, PropertyReferenceException.class})
    public ResponseEntity<Object> handleIllegalArgumentException(
            Exception exception, WebRequest webRequest){

        log.info(generateHandledExceptionMessage(exception.getClass().getSimpleName()));
        return new ResponseEntity<>(generateRestExcResponse(exception, webRequest), HttpStatus.BAD_REQUEST);
    }

    private ExceptionRestResponse generateRestExcResponse(Exception exception, WebRequest webRequest){
        return ExceptionRestResponse.builder()
                .timestamp(new Date())
                .message(exception.getMessage())
                .details(webRequest.getDescription(false))
                .build();
    }

    private String generateHandledExceptionMessage(String exceptionName){
        return "["+getClass().getSimpleName()+"] " + exceptionName + " has been properly handled !";
    }

}
