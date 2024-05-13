package org.spring.codingStory.board.freeBoard.dto;

import lombok.*;

import org.spring.codingStory.board.freeBoard.entity.FreeFileEntity;
import org.spring.codingStory.board.freeBoard.entity.FreeReplyEntity;
import org.spring.codingStory.member.entity.MemberEntity;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class FreeDto {

    private Long id;

    public Long category;

    private String freeTitle;

    private String freeContent;

    private int freeHit;

    private int replyCount;

    private int freeAttachFile;

    private MemberEntity memberEntity;

    private List<FreeFileEntity> freeFileEntityList;

    private List<FreeReplyEntity> freeReplyEntityList;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    private Long memberId;

    private String newFileName;

    private String oldFileName;
}
