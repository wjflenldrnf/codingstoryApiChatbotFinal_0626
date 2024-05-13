package org.spring.codingStory.board.notice.dto;

import lombok.*;
import org.spring.codingStory.board.freeBoard.entity.FreeFileEntity;
import org.spring.codingStory.board.freeBoard.entity.FreeReplyEntity;
import org.spring.codingStory.board.notice.entity.NoticeFileEntity;
import org.spring.codingStory.board.notice.entity.NoticeReplyEntity;
import org.spring.codingStory.member.entity.MemberEntity;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class NoticeDto {

    private Long id;

    public Long category;

    private String noticeTitle;

    private String noticeContent;

    private int noticeHit;

    private int replyCount;

    private int noticeAttachFile;

    private MemberEntity memberEntity;

    private List<NoticeFileEntity> noticeFileEntityList;

    private List<NoticeReplyEntity> noticeReplyEntityList;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    private Long memberId;

    private String newFileName;

    private String oldFileName;
}
