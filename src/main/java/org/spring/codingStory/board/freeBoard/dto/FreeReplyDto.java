package org.spring.codingStory.board.freeBoard.dto;

import lombok.*;
import org.spring.codingStory.board.employee.entity.EmployeeEntity;
import org.spring.codingStory.board.freeBoard.entity.FreeEntity;

import java.time.LocalDateTime;


@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class FreeReplyDto {
    private Long id;

    private String freeReplyWriter;

    private String freeReplyContent;

    private FreeEntity freeEntity;


    private Long freeId;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;


}
