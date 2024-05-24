package org.spring.codingStory.approval.serviceImpl.service;

import org.spring.codingStory.approval.dto.ApprovalDto;
import org.spring.codingStory.approval.entity.ApprovalStatusEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.transaction.Transactional;
import java.io.IOException;

public interface ApprovalService {
    void apvWrite(ApprovalDto approvalDto) throws IOException;


//    void apvWriteMany(ApprovalDto approvalDto) throws IOException;

    // 내가 결재자인 보고서
    @Transactional
    Page<ApprovalDto> apvList(Pageable pageable, String subject, String search, String name);


    //상태별로 뽑기
//    @Transactional
//    Page<ApprovalDto> apvList2(Pageable pageable, String subject
//        , String search, String name,Long id);


    //내가 작성한 보고서
    @Transactional
    Page<ApprovalDto> myApvList(Pageable pageable, String subject, String search, Long memberId);

    ApprovalDto apvDetail(Long id);

    void apvDeleteById(Long id);

    void apvOk(ApprovalDto approvalDto);

    void apvUpdate(ApprovalDto approvalDto) throws IOException;

//    void apvWriteCom(ApprovalDto approvalDto);
}
