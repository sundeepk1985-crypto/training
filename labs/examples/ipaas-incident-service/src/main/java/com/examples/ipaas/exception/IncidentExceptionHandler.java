package com.examples.ipaas.exception;

import com.examples.ipaas.model.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;

//error handling across controllers
@ControllerAdvice
public class IncidentExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorMessage> handleValidationExcpetion(MethodArgumentNotValidException e) {

        List<ObjectError> errors = e.getBindingResult().getAllErrors();
        int size = errors.size();
        StringBuilder errorMsgs = new StringBuilder();

        for(int i = 0; i < size; i++ ) {
            errorMsgs.append(errors.get(i).getDefaultMessage()).append(",");
        }

        ErrorMessage error = new ErrorMessage(400, "Validation Error", errorMsgs.toString());
        return ResponseEntity.badRequest().body(error);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorMessage> handleError(Exception e) {
        ErrorMessage error = new ErrorMessage(500, "Server Error", e.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }
}
