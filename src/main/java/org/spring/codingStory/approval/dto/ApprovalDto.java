package org.spring.codingStory.approval.dto;

import lombok.*;
import org.spring.codingStory.approval.entity.ApprovalDivEntity;
import org.spring.codingStory.approval.entity.ApprovalEntity;
import org.spring.codingStory.approval.entity.ApprovalFileEntity;
import org.spring.codingStory.approval.entity.ApprovalStatusEntity;
import org.spring.codingStory.member.entity.MemberEntity;
import org.springframework.web.multipart.MultipartFile;

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

    //dto 만 따로 추가
    private MultipartFile apvFile;

    private String apvStatus;

    //dto만 따로 추가
    private Long memberId;

    private MemberEntity memberEntity;

    private ApprovalDivEntity approvalDivEntity;

    private ApprovalStatusEntity approvalStatusEntity;

    private List<ApprovalFileEntity> approvalFileEntityList;


    public static ApprovalDto toApvDto(ApprovalEntity approvalEntity) {
        ApprovalDto approvalDto = new ApprovalDto();

       Long id = getId(approvalEntity.getId());
        approvalDto.getApvTitle(approvalEntity.getApvTitle());



        return approvalDto;

    }
}
