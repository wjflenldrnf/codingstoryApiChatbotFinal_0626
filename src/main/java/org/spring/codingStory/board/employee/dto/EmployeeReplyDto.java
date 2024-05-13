package org.spring.codingStory.board.employee.dto;

import lombok.*;
import org.spring.codingStory.board.employee.entity.EmployeeEntity;

import java.time.LocalDateTime;


@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class EmployeeReplyDto {
    private Long id;

    private String empReplyWriter;

    private String empReplyContent;

    private EmployeeEntity employeeEntity;

    private Long employeeId;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;
}
