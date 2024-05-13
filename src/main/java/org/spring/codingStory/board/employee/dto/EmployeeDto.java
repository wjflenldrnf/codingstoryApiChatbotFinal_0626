package org.spring.codingStory.board.employee.dto;

import lombok.*;
import org.spring.codingStory.board.employee.entity.EmployeeFileEntity;
import org.spring.codingStory.board.employee.entity.EmployeeReplyEntity;
import org.spring.codingStory.member.entity.MemberEntity;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class EmployeeDto {

    private Long id;

    public Long category;

    private String empTitle;

    private String empContent;

    private int empHit;

    private int replyCount;

    private int empAttachFile;

    private MemberEntity memberEntity;

    private List<EmployeeFileEntity> employeeFileEntityList;

    private List<EmployeeReplyEntity> employeeReplyEntityList;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    private Long memberId;

    private String newFileName;

    private String oldFileName;

}
