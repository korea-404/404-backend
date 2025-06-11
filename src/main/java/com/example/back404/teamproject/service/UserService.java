package com.example.back404.teamproject.service;

import com.example.back404.teamproject.dto.ResponseDto;
import com.example.back404.teamproject.dto.user.request.UserUpdateRequestDto;
import com.example.back404.teamproject.dto.user.response.GetUserResponseDto;
import jakarta.validation.Valid;

public interface UserService {
    ResponseDto<GetUserResponseDto> getUserInfo(String userEmail);
    ResponseDto<GetUserResponseDto> updateUserInfo(String userEmail, @Valid UserUpdateRequestDto dto);
    ResponseDto<Void> deleteUser(String userEmail);
}