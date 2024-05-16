package org.spring.codingStory.approval.repository;

import org.spring.codingStory.approval.entity.ApprovalEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApprovalRepository extends JpaRepository<ApprovalEntity,Long> {

    Page<ApprovalEntity> findByApvTitleContains(Pageable pageable, String search);

    Page<ApprovalEntity> findByApvContentContains(Pageable pageable, String search);
}
