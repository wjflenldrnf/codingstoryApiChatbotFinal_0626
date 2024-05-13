package org.spring.codingStory.department.dto;

import lombok.*;
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

}
