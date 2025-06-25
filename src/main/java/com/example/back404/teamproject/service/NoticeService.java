package com.example.back404.teamproject.service;

import com.example.back404.teamproject.dto.notice.response.NoticeListResponseDto;
import com.example.back404.teamproject.entity.Notice;
import com.example.back404.teamproject.repository.NoticeRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class NoticeService {
    private final NoticeRepository noticeRepository;

    public List<NoticeListResponseDto> getNoticeList(Long schoolId) {
        List<Notice> notices = noticeRepository.findBySchoolIdOrderByCreatedAtDesc(schoolId);
    }
}
