package org.spring.codingStory.member.repository;

import org.spring.codingStory.member.entity.MemberEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<MemberEntity, Long> {
  Optional<MemberEntity> findByUserEmail(String email);


  Page<MemberEntity> findByDepartmentContains(Pageable pageable, String department);
}
