package com.example.back404.teamproject.common.constants;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor(staticName = "set")
public class ResponseDto<D> {
    private boolean result;
    private String message;
    private D data;
    
    // 성공 응답 생성
    public static <D> ResponseDto<D> setSuccess(String message, D data) { return ResponseDto.set(true, message, data);}
    
    // 실패 응답 생성
    public static <D> ResponseDto<D> setFailed(String message) { return ResponseDto.set(false, message, null);}
}
