package com.thoughtworks.capacity.gtb.mvc.exception;

import com.thoughtworks.capacity.gtb.mvc.dto.ErrorResultDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(UserExitsException.class)
    public ResponseEntity<ErrorResultDto> handle(UserExitsException ex) {
        return getResponseEntity(ex.getMessage());
    }

    @ExceptionHandler(LoginMessageErrorException.class)
    public ResponseEntity<ErrorResultDto> handle(LoginMessageErrorException ex) {
        return getResponseEntity(ex.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResultDto> handle(MethodArgumentNotValidException ex) {
        String errorMessage = ex.getBindingResult().getFieldError().getDefaultMessage();
        return getResponseEntity(errorMessage);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorResultDto> handle(ConstraintViolationException ex) {
        StringBuilder errorMessage = new StringBuilder();
        for (ConstraintViolation<?> constraint : ex.getConstraintViolations()) {
            errorMessage.append(constraint.getMessage()).append(" ");
        }

        return getResponseEntity(errorMessage.toString().trim());
    }

    private ResponseEntity<ErrorResultDto> getResponseEntity(String errorMessage) {
        ErrorResultDto errorResult = new ErrorResultDto(errorMessage);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResult);
    }
}
