package org.spring.codingStory.approval.dto;


import lombok.*;
import org.spring.codingStory.approval.entity.ApprovalEntity;


import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class ApprovalDivDto {

    private Long id;

    private String apvDivName;

    private List<ApprovalEntity> approvalEntityList;
}
