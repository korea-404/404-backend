package com.example.back404.teamproject.service.implementations;

import com.example.back404.teamproject.repository.SchoolRepository;
import com.example.back404.teamproject.security.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final SchoolRepository schoolRepository;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 1. 관리자(학교) 테이블에서 찾기 (학교 코드는 숫자라고 가정)
        try {
            int schoolCode = Integer.parseInt(username);
            return schoolRepository.findBySchoolCode(schoolCode)
                    .map(school -> new CustomUserDetails(
                            String.valueOf(school.getSchoolCode()),
                            school.getSchoolPassword(),
                            "ADMIN"
                    ))
                    .orElseThrow(() -> new UsernameNotFoundException("관리자(학교 코드)를 찾을 수 없습니다: " + username));
        } catch (NumberFormatException e) {
            // 입력된 username이 숫자가 아니면 관리자가 아니므로 예외 발생
            throw new UsernameNotFoundException("관리자(학교 코드)는 숫자여야 합니다: " + username);
        }
    }
}