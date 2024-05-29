package org.spring.codingStory.approval.repository;

import org.spring.codingStory.approval.entity.ApprovalEntity;
import org.spring.codingStory.approval.entity.ApprovalStatusEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ApprovalRepository extends JpaRepository<ApprovalEntity,Long> {

    //내가 결재자인 문서들
    Page<ApprovalEntity> findByApvFnl(Pageable pageable, String name);
//    Page<ApprovalEntity> findByApvFnlAndApprovalStatusEntity_Id(Pageable pageable, String name,Long approvalStatusEntity_Id);
    // ★★★★★★★★JPA로 사용해서 Apv_Fnl 에 name 을 사용 -> Apv_Fnl 이 name 인 것을 출력하라 (name 은 myUserDetails 에서 로그인한 사람의 이름)★★★★★★★★

    Page<ApprovalEntity> findByApvFnlAndApvTitleContaining(Pageable pageable, String name, String search);
//    Page<ApprovalEntity> findByApvFnlAndApprovalStatusEntity_IdAndApvTitleContaining(Pageable pageable, String name, String search, Long approvalStatusEntity_Id);
    // JPA로 사용해서 Apv_Fnl 에 name 을 사용 -> Apv_Fnl이 name인것을 출력하라 (name 은 myUserDetails 에서 로그인한 사람의 이름)

    Page<ApprovalEntity> findByApvFnlAndApvContentContaining(Pageable pageable, String name, String search);
//    Page<ApprovalEntity> findByApvFnlAndApprovalStatusEntity_IdAndApvContentContaining(Pageable pageable, String name, String search, Long approvalStatusEntity_Id);
    // JPA로 사용해서 Apv_Fnl 에 name 을 사용 -> Apv_Fnl이 name인것을 출력하라 (name 은 myUserDetails 에서 로그인한 사람의 이름)


    // 내가 작성한 보고서들
    Page<ApprovalEntity> findByMemberEntity_Id(Pageable pageable, Long memberId);

    Page<ApprovalEntity> findByMemberEntity_IdAndApvTitleContaining(Pageable pageable, Long memberId, String search);

    Page<ApprovalEntity> findByMemberEntity_IdAndApvContentContaining(Pageable pageable, Long memberId, String search);


    // 특정 상태인 보고서들
    Page<ApprovalEntity> findByApvFnlAndApprovalStatusEntity(Pageable pageable, String name, ApprovalStatusEntity approvalStatusEntity);

    Page<ApprovalEntity> findByApvFnlAndApprovalStatusEntityAndApvTitleContaining(Pageable pageable, String name, ApprovalStatusEntity approvalStatusEntity, String search);

    Page<ApprovalEntity> findByApvFnlAndApprovalStatusEntityAndApvContentContaining(Pageable pageable, String name, ApprovalStatusEntity approvalStatusEntity, String search);


//    @Query(value = "select count(*) from apv_tb where apv_Fnl = :name and ApprovalStatusEntity = 1",nativeQuery = true)
//    int findByApvFnlAndApprovalStatusEntityCount(String name, ApprovalStatusEntity approvalStatusEntity);

    Long countByApvFnl(String name);

    Long countByApvFnlAndApprovalStatusEntity_Id(String name, Long approvalStatusEntityId);

    Long countByMemberEntity_Id(Long memberId);
}