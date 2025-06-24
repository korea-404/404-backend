package com.example.back404.teamproject.controller;

import com.example.back404.teamproject.dto.school.request.SchoolApplicationRequestDto;
import com.example.back404.teamproject.service.SchoolApplicationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/school-application")
@RequiredArgsConstructor
public class SchoolApplicationController {

    private final SchoolApplicationService schoolApplicationService;

    @PostMapping
    public ResponseEntity<?> register(@RequestBody @Valid SchoolApplicationRequestDto dto) {
        return ResponseEntity.ok(schoolApplicationService.register(dto));
    }

    @PutMapping("/{id}/approve")
    public ResponseEntity<?> approve(@PathVariable Long id) {
        System.out.println("Approve 진입: id = " + id);
        return ResponseEntity.ok(schoolApplicationService.approve(id));
    }

    @PutMapping("/{id}/reject")
    public ResponseEntity<?> reject(@PathVariable Long id) {
        System.out.println("Reject 진입: id = " + id);
        return ResponseEntity.ok(schoolApplicationService.reject(id));
    }
}
