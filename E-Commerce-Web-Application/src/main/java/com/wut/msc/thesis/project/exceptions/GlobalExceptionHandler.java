package com.wut.msc.thesis.project.exceptions;

import org.hibernate.annotations.NotFoundAction;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@ControllerAdvice
public class GlobalExceptionHandler {

    ErrorHandlerDetails errorHandlerDetails = new ErrorHandlerDetails();

    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(CustomerExistException.class)
    public ResponseEntity<?> CustomerExistException(CustomerExistException exception){
        errorHandlerDetails = getErrorDetailsHttpStatusOk(exception);
        return new ResponseEntity<>(errorHandlerDetails, HttpStatus.OK);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> MethodArgumentNotValidException(MethodArgumentNotValidException exception){
        Map<String, String> errors = new HashMap<>();
        exception.getBindingResult().getFieldErrors().forEach(error -> {
            errors.put(error.getField(), error.getDefaultMessage());
        });
        return errors;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<?> DataIntegrityViolationException(DataIntegrityViolationException exception){
        errorHandlerDetails = getErrorDetailsHttpStatusBadRequest(exception);
        return new ResponseEntity<>(errorHandlerDetails, HttpStatus.BAD_REQUEST);
    }


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ResponseStatusException.class)
    public Map<String, String> ResponseStatusException(ResponseStatusException exception) {
        Map<String, String> errors = new HashMap<>();
        if (exception.getStatusCode() == INTERNAL_SERVER_ERROR) {
            errors.put("error = ", "Internal Server Error");
            errors.put("timestamp = ", LocalDateTime.now().toString());
            errors.put("status = ", exception.getReason());
            errors.put("message = ", exception.getMessage());
            errors.put("trace = ", Arrays.toString(exception.getStackTrace()));
        }
        return errors;
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(HandleNotFoundException.class)
    public ResponseEntity<?> HandleNotFoundException(Exception  exception){
        errorHandlerDetails = getErrorDetailsHttpStatusNotFound(exception);
        return new ResponseEntity<>(errorHandlerDetails, HttpStatus.NOT_FOUND);
    }

    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(ProductExistException.class)
    public ResponseEntity<?> ProductExistException(ProductExistException exception){
        errorHandlerDetails = getErrorDetailsHttpStatusOk(exception);
        return new ResponseEntity<>(errorHandlerDetails, HttpStatus.OK);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<?> ProductNotFoundException(Exception exception){
        errorHandlerDetails = getErrorDetailsHttpStatusNotFound(exception);
        return new ResponseEntity<>(errorHandlerDetails, HttpStatus.NOT_FOUND);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(AddressNotFoundException.class)
    public ResponseEntity<?> AddressNotFoundException(AddressNotFoundException exception){
        errorHandlerDetails = getErrorDetailsHttpStatusNotFound(exception);
        return new ResponseEntity<>(errorHandlerDetails, HttpStatus.NOT_FOUND);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(CartNotFoundException.class)
    public ResponseEntity<?> CartNotFoundException(CartNotFoundException exception){
        errorHandlerDetails = getErrorDetailsHttpStatusNotFound(exception);
        return new ResponseEntity<>(errorHandlerDetails, HttpStatus.NOT_FOUND);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(CustomerNotFoundException.class)
    public ResponseEntity<?> CustomerNotFoundException(CustomerNotFoundException exception){
        errorHandlerDetails = getErrorDetailsHttpStatusNotFound(exception);
        return new ResponseEntity<>(errorHandlerDetails, HttpStatus.NOT_FOUND);
    }

    @ResponseStatus(HttpStatus.OK)
    private ErrorHandlerDetails getErrorDetailsHttpStatusOk(Exception exception) {
        errorHandlerDetails.setStatus(HttpStatus.OK.value());
        errorHandlerDetails.setTrace(HttpStatus.OK.name());
        errorHandlerDetails.setError(exception.getMessage());
        errorHandlerDetails.setTimestamp(LocalDateTime.now());
        errorHandlerDetails.setPath(exception.getCause().toString());
        return errorHandlerDetails;
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    private ErrorHandlerDetails getErrorDetailsHttpStatusNotFound(Exception exception) {
        errorHandlerDetails.setStatus(HttpStatus.NOT_FOUND.value());
        errorHandlerDetails.setTrace(HttpStatus.NOT_FOUND.name());
        errorHandlerDetails.setError(exception.getMessage());
        errorHandlerDetails.setTimestamp(LocalDateTime.now());
        errorHandlerDetails.setPath(exception.getCause().toString());
        return errorHandlerDetails;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    private ErrorHandlerDetails getErrorDetailsHttpStatusBadRequest(Exception exception) {
        errorHandlerDetails.setStatus(HttpStatus.BAD_REQUEST.value());
        errorHandlerDetails.setTrace(HttpStatus.BAD_REQUEST.name());
        errorHandlerDetails.setError(exception.getMessage());
        errorHandlerDetails.setTimestamp(LocalDateTime.now());
        errorHandlerDetails.setPath(exception.getCause().toString());
        return errorHandlerDetails;
    }
}
