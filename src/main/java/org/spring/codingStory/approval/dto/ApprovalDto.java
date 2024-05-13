package org.spring.codingStory.approval.dto;

import lombok.*;
import org.spring.codingStory.approval.entity.ApprovalDivEntity;
import org.spring.codingStory.approval.entity.ApprovalFileEntity;
import org.spring.codingStory.approval.entity.ApprovalOkEntity;
import org.spring.codingStory.member.entity.MemberEntity;

import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class ApprovalDto {

    private Long id;

    private String apvTitle;

    private String apvContent;

    private int apvAttachFile;

    private String apvFnl;

    private String apvNo;

    private String apvDiv;

    private MemberEntity memberEntity;

    private ApprovalDivEntity approvalDivEntity;

    private ApprovalOkEntity approvalOkEntity;

    private List<ApprovalFileEntity> approvalFileEntityList;


}
