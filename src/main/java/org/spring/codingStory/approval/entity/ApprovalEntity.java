package org.spring.codingStory.approval.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.spring.codingStory.approval.dto.ApprovalDto;
import org.spring.codingStory.contraint.BaseTimeEntity;
import org.spring.codingStory.member.entity.MemberEntity;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "apv_tb")
public class ApprovalEntity extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "apv_id")
    private Long id;

    @Column(nullable = false)
    private String apvTitle;

    @Column(nullable = false)
    private String apvContent;

    @Column(nullable = false)
    private int apvAttachFile;

    @Column(nullable = true)
    private String apvFnl;
    //최종 결재 승인자(사람)
    
//    @Column(nullable = false)
//    private String apvDiv;
//    //결재 종류

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.PERSIST)
    @JoinColumn(name = "member_id")
    private MemberEntity memberEntity;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "apvDiv_id")
    private ApprovalDivEntity approvalDivEntity;
    // 보고서의 종류(휴무계획서, 일반 결재서)
    
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "apvStatus_id")
    private ApprovalStatusEntity approvalStatusEntity;
    //보고서의 현재 상태(진행중, 완료, 반려)

    @JsonIgnore
    @OneToMany(mappedBy = "approvalEntity"
            , fetch = FetchType.LAZY
            , cascade = CascadeType.REMOVE)
    private List<ApprovalFileEntity> approvalFileEntityList;

    //dto -> entity (파일 없을 때 보고서 작성)
    public static ApprovalEntity toWriteApv(ApprovalDto approvalDto) {
        ApprovalEntity approvalEntity = new ApprovalEntity();

//        approvalEntity.setId(approvalDto.getId());
        approvalEntity.setApvTitle(approvalDto.getApvTitle());
        approvalEntity.setApvContent(approvalDto.getApvContent());
        approvalEntity.setApvAttachFile(0);
        approvalEntity.setApprovalStatusEntity(approvalDto.getApprovalStatusEntity());
        approvalEntity.setMemberEntity(approvalDto.getMemberEntity());
        approvalEntity.setApprovalDivEntity(approvalDto.getApprovalDivEntity());
        approvalEntity.setApprovalStatusEntity(approvalDto.getApprovalStatusEntity());
        
        return approvalEntity;
    }
    
    // 파일 있을 때 보고서 작성
    public static ApprovalEntity toWriteApv1(ApprovalDto approvalDto) {
        ApprovalEntity approvalEntity = new ApprovalEntity();

//        approvalEntity.setId(approvalDto.getId());
        approvalEntity.setApvTitle(approvalDto.getApvTitle());
        approvalEntity.setApvContent(approvalDto.getApvContent());
        approvalEntity.setApvAttachFile(1);
        approvalEntity.setApprovalStatusEntity(approvalDto.getApprovalStatusEntity());
        approvalEntity.setMemberEntity(approvalDto.getMemberEntity());
        approvalEntity.setApprovalDivEntity(approvalDto.getApprovalDivEntity());
        approvalEntity.setApprovalStatusEntity(approvalDto.getApprovalStatusEntity());
        approvalEntity.setApprovalFileEntityList(approvalDto.getApprovalFileEntityList());

        return approvalEntity;
    }

}
