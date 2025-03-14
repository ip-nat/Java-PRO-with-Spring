package org.ippnat.controller;

import org.ippnat.model.dto.ErrorResponseDto;
import org.ippnat.model.exception.InsufficientBalanceException;
import org.ippnat.model.exception.ProductNotFoundException;
import org.ippnat.model.exception.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static org.ippnat.model.dto.ErrorCodeEnum.INSUFFICIENT_BALANCE;
import static org.ippnat.model.dto.ErrorCodeEnum.INTERNAL_ERROR;
import static org.ippnat.model.dto.ErrorCodeEnum.PRODUCT_NOT_FOUND;
import static org.ippnat.model.dto.ErrorCodeEnum.USER_NOT_FOUND;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> handleProductNotFoundException(ProductNotFoundException ex) {
        ErrorResponseDto error = new ErrorResponseDto(PRODUCT_NOT_FOUND, ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(InsufficientBalanceException.class)
    public ResponseEntity<ErrorResponseDto> handleInsufficientBalanceException(InsufficientBalanceException ex) {
        ErrorResponseDto error = new ErrorResponseDto(INSUFFICIENT_BALANCE, ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> handleUserNotFoundException(UserNotFoundException ex) {
        ErrorResponseDto error = new ErrorResponseDto(USER_NOT_FOUND, ex.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDto> handleGenericException(Exception ex) {
        ErrorResponseDto error = new ErrorResponseDto(INTERNAL_ERROR, "Something went wrong");
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }

}
