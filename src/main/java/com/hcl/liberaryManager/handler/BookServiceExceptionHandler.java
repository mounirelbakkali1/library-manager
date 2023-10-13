package com.hcl.liberaryManager.handler;


import com.hcl.liberaryManager.dto.ApiResponse;
import com.hcl.liberaryManager.dto.ErrorDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.stream.Collectors;

@RestControllerAdvice
public class BookServiceExceptionHandler {


    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiResponse<?> handleException(MethodArgumentNotValidException e){
        return ApiResponse.builder()
                .status("failure")
                .errors(e.getBindingResult()
                        .getFieldErrors()
                        .stream()
                        .map(err ->
                            ErrorDto.builder()
                                    .field(err.getField())
                                    .message(err.getDefaultMessage())
                                    .build()
                        ).collect(Collectors.toList())
                ).build();
    }
}
