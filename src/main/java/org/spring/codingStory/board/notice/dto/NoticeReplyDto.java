package org.spring.codingStory.board.notice.dto;

import lombok.*;
import org.spring.codingStory.board.freeBoard.entity.FreeEntity;
import org.spring.codingStory.board.notice.entity.NoticeEntity;

import java.time.LocalDateTime;


@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class NoticeReplyDto {
    private Long id;

    private String noticeReplyWriter;

    private String noticeReplyContent;

    private NoticeEntity noticeEntity;

    private Long noticeId;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;


}
