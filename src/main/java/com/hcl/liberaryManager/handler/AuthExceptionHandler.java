package com.hcl.liberaryManager.handler;


import com.hcl.liberaryManager.dto.ApiResponse;
import com.hcl.liberaryManager.dto.ErrorDto;
import com.hcl.liberaryManager.exception.NewSignUpException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class AuthExceptionHandler {

    @ExceptionHandler(NewSignUpException.class)
    public ResponseEntity<?> signupException(NewSignUpException exception){
        ApiResponse<Object> apiResponse = ApiResponse.builder()
                .errors(List.of(
                        ErrorDto.builder()
                                .message(exception.getMessage())
                                .build()

                )).build();
        return ResponseEntity.badRequest().body(apiResponse);

    }
}
