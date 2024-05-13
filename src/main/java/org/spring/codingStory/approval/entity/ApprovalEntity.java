package org.spring.codingStory.approval.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
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
public class ApprovalEntity {

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

    @Column(nullable = false)
    private String apvFnl;

    @Column(nullable = false)
    private String apvNo;

    @Column(nullable = false)
    private String apvDiv;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private MemberEntity memberEntity;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "apvDiv_id")
    private ApprovalDivEntity approvalDivEntity;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "apvOk_id")
    private ApprovalOkEntity approvalOkEntity;

    @JsonIgnore
    @OneToMany(mappedBy = "approvalEntity"
            , fetch = FetchType.LAZY
            , cascade = CascadeType.REMOVE)
    private List<ApprovalFileEntity> approvalFileEntityList;




}
