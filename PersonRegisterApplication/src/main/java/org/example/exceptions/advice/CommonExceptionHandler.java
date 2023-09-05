package org.example.exceptions.advice;

import org.example.dtos.ErrorResponse;
import org.example.exceptions.PersonAlreadyExistsException;
import org.example.exceptions.RecordNotFoundException;
import org.example.exceptions.WrongUserInputException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CommonExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(RecordNotFoundException.class)
    public ResponseEntity<Object> handleRecordNotFound(RecordNotFoundException ex) {
        return ResponseEntity.badRequest().body(new ErrorResponse("Record of given id not found"));
    }

    @ExceptionHandler(WrongUserInputException.class)
    public ResponseEntity<Object> handleWrongUserInput(WrongUserInputException ex) {
        return ResponseEntity.badRequest().body(new ErrorResponse(ex.getMessage()));
    }

    @ExceptionHandler(PersonAlreadyExistsException.class)
    public ResponseEntity<Object> handlePersonAlreadyExists(PersonAlreadyExistsException ex) {
        return ResponseEntity.badRequest().body(new ErrorResponse("Person of given id already exists"));
    }
}