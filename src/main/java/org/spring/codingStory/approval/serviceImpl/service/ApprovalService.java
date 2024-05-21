package org.spring.codingStory.approval.serviceImpl.service;

import org.spring.codingStory.approval.dto.ApprovalDto;
import org.spring.codingStory.config.MyUserDetails;
import org.spring.codingStory.member.dto.MemberDto;
import org.spring.codingStory.member.entity.MemberEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;

public interface ApprovalService {
    void apvWrite(ApprovalDto approvalDto) throws IOException;


    // 내가 결재자인 보고서
//    @Transactional
//    Page<ApprovalDto> apvList(Pageable pageable, String subject
//        , String search, String name, Long i);
    @Transactional
    Page<ApprovalDto> apvList(Pageable pageable, String subject, String search, String name);

    //내가 작성한 보고서
    @Transactional
    Page<ApprovalDto> myApvList(Pageable pageable, String subject, String search, Long memberId);

    ApprovalDto apvDetail(Long id);

    void apvDeleteById(Long id);

    void apvOk(ApprovalDto approvalDto);
}
