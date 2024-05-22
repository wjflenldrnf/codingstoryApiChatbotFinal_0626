package org.spring.codingStory.department.serviceimpl.service;

import lombok.RequiredArgsConstructor;
import org.spring.codingStory.department.dto.DepartmentDto;
import org.spring.codingStory.department.entity.DepartmentEntity;
import org.spring.codingStory.department.repository.DepartmentRepository;
import org.spring.codingStory.department.serviceimpl.DepartmentServiceInterface;
import org.spring.codingStory.member.dto.MemberDto;
import org.spring.codingStory.member.entity.MemberEntity;
import org.spring.codingStory.member.repository.MemberRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.lang.reflect.Member;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Transactional
@Service
@RequiredArgsConstructor
public class DepartmentService implements DepartmentServiceInterface {

  private final DepartmentRepository departmentRepository;
  private final MemberRepository memberRepository;


  @Override
  public DepartmentEntity addDepartment(DepartmentDto departmentDto) {
    //DepartmentDto로부터 DepartmentEntity 생성
    DepartmentEntity departmentEntity=DepartmentEntity.builder()
        .dptName(departmentDto.getDptName())
        .location(departmentDto.getLocation())
        .build();

    //DepartmentRepository를 사용하여 부서를 저장하고 반환
    return departmentRepository.save(departmentEntity);
  }

  @Override
  public List<DepartmentEntity> getAllDepartments() {
    return departmentRepository.findAll();
  }

  @Override
  public DepartmentDto getDepartmentByIdWithMembers(Long deptId) {
    DepartmentEntity department = departmentRepository.findById(deptId)
        .orElseThrow(() -> new RuntimeException("Department not found"));

    DepartmentDto dto = new DepartmentDto();
    dto.setId(department.getId());
    dto.setDptName(department.getDptName());
    dto.setLocation(department.getLocation());
    dto.setMemberCount(department.getMemberCount());

    // 멤버 리스트 설정
   List<MemberEntity> memberEntityList=department.getMemberEntityList();
   List<MemberDto> memberDtoList= memberEntityList.stream()
           .map(memberEntity -> {
             MemberDto memberDto =new MemberDto();
             memberDto.setId(memberEntity.getId());
             memberDto.setRole(memberEntity.getRole());
             memberDto.setName(memberEntity.getName());
             return memberDto;
           })
               .collect(Collectors.toList());

   dto.setMemberDtoList(memberDtoList);

   return dto;
  }

  @Override
  public void adddMemberToDepartment(Long deptId, MemberDto memberDto) {
    DepartmentEntity department =departmentRepository.findById(deptId)
        .orElseThrow(()-> new RuntimeException("Department not found"));

    MemberEntity memberEntity = MemberEntity.builder()
        .departmentEntity(department)
        .name(memberDto.getName())
        .role(memberDto.getRole())
        .build();

    memberRepository.save(memberEntity);
  }


}



