package org.spring.codingStory.approval.repository;

import org.spring.codingStory.approval.entity.ApprovalEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApprovalRepository extends JpaRepository<ApprovalEntity,Long> {

}
