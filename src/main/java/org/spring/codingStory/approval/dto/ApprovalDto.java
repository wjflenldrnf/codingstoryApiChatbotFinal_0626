package org.spring.codingStory.approval.dto;

import lombok.*;
import org.spring.codingStory.approval.entity.ApprovalDivEntity;
import org.spring.codingStory.approval.entity.ApprovalEntity;
import org.spring.codingStory.approval.entity.ApprovalFileEntity;
import org.spring.codingStory.approval.entity.ApprovalStatusEntity;
import org.spring.codingStory.member.entity.MemberEntity;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Locale;


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

    //dto 만 따로 추가
    private MultipartFile apvFile;

    private String apvStatus;

    //dto만 따로 추가
    private Long memberId;

    private MemberEntity memberEntity;

    private ApprovalDivEntity approvalDivEntity;

    private ApprovalStatusEntity approvalStatusEntity;

    private List<ApprovalFileEntity> approvalFileEntityList;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    public static ApprovalDto toApvDtoList(ApprovalEntity approvalEntity) {
        ApprovalDto approvalDto = new ApprovalDto();

    approvalDto.setId(approvalEntity.getId());
    approvalDto.setApvTitle(approvalEntity.getApvTitle());
    approvalDto.setApvContent(approvalEntity.getApvContent());
    approvalDto.setApprovalStatusEntity(approvalEntity.getApprovalStatusEntity());
    approvalDto.setApvAttachFile(approvalDto.getApvAttachFile());
    approvalDto.setMemberEntity(approvalEntity.getMemberEntity());
    approvalDto.setApprovalDivEntity(approvalEntity.getApprovalDivEntity());
    approvalDto.setApprovalFileEntityList(approvalEntity.getApprovalFileEntityList());
    approvalDto.setCreateTime(approvalEntity.getCreateTime());
    approvalDto.setUpdateTime(approvalEntity.getUpdateTime());

    return approvalDto;

    }
}
