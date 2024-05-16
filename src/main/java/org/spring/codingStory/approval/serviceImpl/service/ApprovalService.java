package org.spring.codingStory.approval.serviceImpl.service;

import org.spring.codingStory.approval.dto.ApprovalDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.IOException;

public interface ApprovalService {
    void apvWrite(ApprovalDto approvalDto) throws IOException;

    Page<ApprovalDto> apvList(Pageable pageable, String subject, String search);
}
