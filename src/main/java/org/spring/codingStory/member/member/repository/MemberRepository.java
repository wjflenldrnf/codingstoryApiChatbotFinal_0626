package org.spring.codingStory.member.member.repository;

import org.spring.codingStory.member.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<MemberEntity,Long> {
  Optional<MemberEntity> findByUserEmail(String email);

}
