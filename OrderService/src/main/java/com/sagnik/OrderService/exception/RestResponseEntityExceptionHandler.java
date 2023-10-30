package com.sagnik.OrderService.exception;


import com.sagnik.OrderService.external.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice //this is means this will act asa controller advice and when the controller encounters any problem its handled by this class
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ErrorResponse> handleCustomException(CustomException exception) {
        return new ResponseEntity<>(new ErrorResponse().builder()
                .errorMessage(exception.getMessage())
                .errcode(exception.getErrorCode())
                .build(), HttpStatus.valueOf(exception.getStatus()));
    }
}
//MECHANISM
//**-- Whenever theres an exception from placeOrder
//**--  placeOrder will call productservice which will identify the errror and throwback
//**- Feignclient decoder will decode the error and throw the error back
//**- the error thrown back will be handled by the @ControllerAdvice
