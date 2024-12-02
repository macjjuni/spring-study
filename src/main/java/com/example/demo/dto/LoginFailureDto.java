package com.example.demo.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class LoginFailureDto {
    private String errorCode;
    private String errorMessage;

    @Builder
    public LoginFailureDto(String errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }
}
