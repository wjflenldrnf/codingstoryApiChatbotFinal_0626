package org.spring.codingStory.member.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.spring.codingStory.approval.entity.ApprovalEntity;
import org.spring.codingStory.contraint.BaseTimeEntity;
import org.spring.codingStory.department.entity.DepartmentEntity;
import org.spring.codingStory.member.role.Role;
import org.spring.codingStory.pay.entity.PayEntity;
import org.spring.codingStory.mRank.entity.RankEntity;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "member")
public class MemberEntity extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @Column(nullable = false, unique = true)
    private String userEmail;

    @Column(nullable = false)
    private String userPw;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String department;

    @Column(nullable = false)
    private String mRank;

    @Column(nullable = true) // 기본이 널 허용
    private String address;

    @Column(nullable = true)
    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(nullable = true)
    private String memberFileName;

    @Column(nullable = false)
    private int memberAttachFile;

//  1:N
    @JsonIgnore // ajax시 순환참조 방지
    @OneToMany(mappedBy = "memberEntity"
            , fetch = FetchType.LAZY
            , cascade = CascadeType.REMOVE)
    private List<MemberFileEntity> memberFileEntityList;

    @JsonIgnore // ajax시 순환참조 방지
    @OneToMany(mappedBy = "memberEntity"
            , fetch = FetchType.LAZY
            , cascade = CascadeType.REMOVE)
    private List<PayEntity> payEntityList;

    @JsonIgnore // ajax시 순환참조 방지
    @OneToMany(mappedBy = "memberEntity"
            , fetch = FetchType.LAZY
            , cascade = CascadeType.REMOVE)
    private List<ApprovalEntity> approvalEntityList;


//  N:1
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rank_id")
    private RankEntity rankEntity;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id")
    private DepartmentEntity departmentEntity;

}
