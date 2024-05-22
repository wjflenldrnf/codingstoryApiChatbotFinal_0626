package org.spring.codingStory.department.dto;

import lombok.*;
import org.spring.codingStory.member.dto.MemberDto;
import org.spring.codingStory.member.entity.MemberEntity;

import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class DepartmentDto {
    private Long id;

    private String dptName;

    private List<MemberEntity> memberEntityList;

    private String location; // 부서 위치 필드 추가

    private int memberCount; // 소속 인원 수 필드 추가

    private List<MemberDto> memberDtoList;


}
