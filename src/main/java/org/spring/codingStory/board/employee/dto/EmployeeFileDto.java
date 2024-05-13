package org.spring.codingStory.board.employee.dto;

import lombok.*;
import org.spring.codingStory.board.employee.entity.EmployeeEntity;

import java.time.LocalDateTime;


@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class EmployeeFileDto {
    private Long id;

    private String empNewFileName;

    private String empOldFileName;

    private EmployeeEntity employeeEntity;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;
}
