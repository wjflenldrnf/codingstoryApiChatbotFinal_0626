package org.spring.codingStory.approval.repository;

import org.spring.codingStory.approval.dto.ApprovalDto;
import org.spring.codingStory.approval.entity.ApprovalEntity;
import org.spring.codingStory.member.dto.MemberDto;
import org.spring.codingStory.member.entity.MemberEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

@Repository
public interface ApprovalRepository extends JpaRepository<ApprovalEntity,Long> {

    //내가 결재자인 문서들
//    Page<ApprovalEntity> findByApvFnlAndApprovalStatusEntity(Pageable pageable,Long i, String name);
    Page<ApprovalEntity> findByApvFnl(Pageable pageable, String name);
    // ★★★★★★★★JPA로 사용해서 Apv_Fnl 에 name 을 사용 -> Apv_Fnl 이 name 인 것을 출력하라 (name 은 myUserDetails 에서 로그인한 사람의 이름)★★★★★★★★

//    Page<ApprovalEntity> findByApvFnlAndApprovalStatusEntityAndApvTitleContaining( Pageable pageable, String name,Long i, String search);
    Page<ApprovalEntity> findByApvFnlAndApvTitleContaining(Pageable pageable, String name, String search);
    // JPA로 사용해서 Apv_Fnl 에 name 을 사용 -> Apv_Fnl이 name인것을 출력하라 (name 은 myUserDetails 에서 로그인한 사람의 이름)

//    Page<ApprovalEntity> findByApvFnlAndApprovalStatusEntityAndApvContentContaining( Pageable pageable, String name,Long i , String search);
    Page<ApprovalEntity> findByApvFnlAndApvContentContaining(Pageable pageable, String name, String search);
    // JPA로 사용해서 Apv_Fnl 에 name 을 사용 -> Apv_Fnl이 name인것을 출력하라 (name 은 myUserDetails 에서 로그인한 사람의 이름)

    
    // 내가 작성한 보고서들
    Page<ApprovalEntity> findByMemberEntity_Id(Pageable pageable, Long memberId);

    Page<ApprovalEntity> findByMemberEntity_IdAndApvTitleContaining(Pageable pageable, Long memberId, String search);

    Page<ApprovalEntity> findByMemberEntity_IdAndApvContentContaining(Pageable pageable, Long memberId, String search);
    //

}
