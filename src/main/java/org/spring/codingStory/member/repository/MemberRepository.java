package org.spring.codingStory.member.repository;

import org.spring.codingStory.member.entity.MemberEntity;
import org.spring.codingStory.member.role.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<MemberEntity, Long> {
  Optional<MemberEntity> findByUserEmail(String email);


  Page<MemberEntity> findByDepartmentContains(Pageable pageable, String department);

  @Query(value = "SELECT * FROM member WHERE role = 'member' order by member_Id", nativeQuery = true)
  Page<MemberEntity> findByRoleMember(Pageable pageable);

  Page<MemberEntity> findByRoleAndDepartmentContains(Pageable pageable, Role role, String department);
  Page<MemberEntity> findByRole(Pageable pageable, Role role);

}
