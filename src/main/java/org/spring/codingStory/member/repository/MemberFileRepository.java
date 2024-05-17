package org.spring.codingStory.member.repository;

import org.spring.codingStory.member.entity.MemberFileEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberFileRepository extends JpaRepository<MemberFileEntity,Long> {

}
