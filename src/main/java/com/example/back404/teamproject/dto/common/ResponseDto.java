package com.example.back404.teamproject.dto.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ResponseDto<T> {
    private String message;
    private T data;

    public static <T> ResponseDto<T> of(String message, T data) {
        return new ResponseDto<>(message, data);
    }
}
