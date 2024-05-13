package org.spring.codingStory.board.freeBoard.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.spring.codingStory.board.employee.entity.EmployeeFileEntity;
import org.spring.codingStory.board.employee.entity.EmployeeReplyEntity;
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
@Table(name = "freeBoard")
public class FreeEntity extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "free_id")
    private Long id;

    @Column(nullable = false)
    public Long category;

    @Column(nullable = false)
    private String freeTitle;

    @Column(nullable = false)
    private String freeContent;

    @Column(columnDefinition = "integer default 0", nullable = false)
    private int freeHit;

    @Column(columnDefinition = "integer default 0", nullable = false)
    private int replyCount;

    @Column(nullable = false)
    private int freeAttachFile;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private MemberEntity memberEntity;

    @JsonIgnore
    @OneToMany(mappedBy = "freeEntity"
            , fetch = FetchType.LAZY
            , cascade = CascadeType.REMOVE)
    private List<FreeFileEntity> freeFileEntityList;

    @JsonIgnore // ajax시 순환참조 방지
    @OneToMany(mappedBy = "freeEntity"
            , fetch = FetchType.LAZY
            , cascade = CascadeType.REMOVE)
     private List<FreeReplyEntity> freeReplyEntityList;



}
