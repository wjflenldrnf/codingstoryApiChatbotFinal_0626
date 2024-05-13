package org.spring.codingStory.board.employee.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
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
@Table(name = "employee")
public class EmployeeEntity extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employee_id")
    private Long id;

    @Column(nullable = false)
    public Long category;

    @Column(nullable = false)
    private String empTitle;

    @Column(nullable = false)
    private String empContent;

    @Column(columnDefinition = "integer default 0", nullable = false)
    private int empHit;

    @Column(columnDefinition = "integer default 0", nullable = false)
    private int replyCount;

    @Column(nullable = false)
    private int empAttachFile;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private MemberEntity memberEntity;

    @JsonIgnore
    @OneToMany(mappedBy = "employeeEntity"
            , fetch = FetchType.LAZY
            , cascade = CascadeType.REMOVE)
    private List<EmployeeFileEntity> employeeFileEntityList;

    @JsonIgnore // ajax시 순환참조 방지
    @OneToMany(mappedBy = "employeeEntity"
            , fetch = FetchType.LAZY
            , cascade = CascadeType.REMOVE)
    private List<EmployeeReplyEntity> employeeReplyEntityList;



}
