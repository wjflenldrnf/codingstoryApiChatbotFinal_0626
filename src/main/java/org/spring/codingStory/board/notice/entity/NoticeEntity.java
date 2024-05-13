package org.spring.codingStory.board.notice.entity;

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
@Table(name = "notice")
public class NoticeEntity extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "notice_id")
    private Long id;

    @Column(nullable = false)
    public Long category;

    @Column(nullable = false)
    private String noticeTitle;

    @Column(nullable = false)
    private String noticeContent;

    @Column(columnDefinition = "integer default 0", nullable = false)
    private int noticeHit;

    @Column(columnDefinition = "integer default 0", nullable = false)
    private int replyCount;

    @Column(nullable = false)
    private int noticeAttachFile;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private MemberEntity memberEntity;

    @JsonIgnore
    @OneToMany(mappedBy = "noticeEntity"
            , fetch = FetchType.LAZY
            , cascade = CascadeType.REMOVE)
    private List<NoticeFileEntity> noticeFileEntityList;

    @JsonIgnore // ajax시 순환참조 방지
    @OneToMany(mappedBy = "noticeEntity"
            , fetch = FetchType.LAZY
            , cascade = CascadeType.REMOVE)
    private List<NoticeReplyEntity> noticeReplyEntityList;



}
