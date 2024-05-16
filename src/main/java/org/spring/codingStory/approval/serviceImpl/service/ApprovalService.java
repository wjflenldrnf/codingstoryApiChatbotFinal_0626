package org.spring.codingStory.approval.serviceImpl.service;

import org.spring.codingStory.approval.dto.ApprovalDto;

import java.io.IOException;

public interface ApprovalService {
    void apvWrite(ApprovalDto approvalDto) throws IOException;
}
