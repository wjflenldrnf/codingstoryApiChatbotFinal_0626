package org.spring.codingStory.department.serviceimpl;

import org.spring.codingStory.department.dto.DepartmentDto;
import org.spring.codingStory.department.entity.DepartmentEntity;
import org.spring.codingStory.member.dto.MemberDto;

import java.util.List;

public interface DepartmentServiceInterface {
  DepartmentEntity addDepartment(DepartmentDto departmentDto);

  List<DepartmentEntity> getAllDepartments();

  DepartmentDto getDepartmentByIdWithMembers(Long deptId);


  void adddMemberToDepartment(Long deptId, MemberDto memberDto);

  List<DepartmentEntity> getParentDepartments();

  void addSubDepartment(Long parentDeptId, DepartmentDto departmentDto);


}
