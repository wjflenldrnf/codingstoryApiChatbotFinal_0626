package org.spring.codingStory.board.notice.dto;

import lombok.*;
import org.spring.codingStory.board.notice.entity.NoticeEntity;

import java.time.LocalDateTime;


@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class NoticeFileDto {
    private Long id;

    private String noticeNewFileName;

    private String noticeOldFileName;

    private NoticeEntity noticeEntity;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;
}
