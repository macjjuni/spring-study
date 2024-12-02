
package com.example.demo.config;

import com.example.demo.dto.LoginFailureDto;
import com.example.demo.enumeration.ResultCodeEnum;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(GeneralException.class)
    public ResponseEntity<LoginFailureDto> handleGeneralException(GeneralException ex) {

        ResultCodeEnum resultCode = ex.getResultCode();
        LoginFailureDto failureDto = LoginFailureDto.builder()
                .errorCode(String.valueOf(resultCode.getCode()))
                .errorMessage(resultCode.getMessage())
                .build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(failureDto);
    }
}
