package org.spring.codingStory.fullcalender.reposiory;

import org.spring.codingStory.fullcalender.entity.FullCalenderEntity;
import org.spring.codingStory.member.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FullCalenderRepository extends JpaRepository<FullCalenderEntity,Long> {
  List<FullCalenderEntity> findByMemberEntity(MemberEntity memberEntity);
}
