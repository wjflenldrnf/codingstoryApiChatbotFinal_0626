package org.spring.codingStory.approval.dto;

import lombok.*;
import org.spring.codingStory.approval.entity.ApprovalEntity;


import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class ApprovalStatusDto {

    private Long id;

    private String apvOk;

    private List<ApprovalEntity> approvalEntityList;

}
