package com.wellsfargo.fsd.cms.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.wellsfargo.fsd.cms.exception.InterviewException;
import com.wellsfargo.fsd.cms.exception.UserException;

@RestControllerAdvice
public class GlobalExceptionController {
    @ExceptionHandler(UserException.class)
    public ResponseEntity<String> handleUserException(UserException exp) {
        return new ResponseEntity<String>(exp.getMessage(),HttpStatus.BAD_REQUEST);
    }
    
    @ExceptionHandler(InterviewException.class)
    public ResponseEntity<String> handleInterviewException(UserException exp) {
        return new ResponseEntity<String>(exp.getMessage(),HttpStatus.BAD_REQUEST);
    }
    
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception exp) {
        return new ResponseEntity<String>(exp.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
