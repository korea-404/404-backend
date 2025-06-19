package com.example.back404.teamproject.controller;

import com.example.back404.teamproject.dto.ResponseDto;
import com.example.back404.teamproject.dto.auth.SchoolApplicationRequestDto;
import com.example.back404.teamproject.service.SchoolApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/school-application")
@RequiredArgsConstructor
public class SchoolApplicationController {

    private final SchoolApplicationService service;

    @PostMapping
    public ResponseDto<Long> register(@RequestBody SchoolApplicationRequestDto requestDto) {
        return service.register(requestDto);
    }

    @GetMapping("/{id}")
    public ResponseDto<?> getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PutMapping("/{id}/approve")
    public ResponseDto<String> approve(@PathVariable Long id) {
        return service.approve(id);
    }
}
