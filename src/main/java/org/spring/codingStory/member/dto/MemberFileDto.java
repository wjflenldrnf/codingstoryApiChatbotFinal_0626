package org.spring.codingStory.member.dto;

import lombok.*;
import org.spring.codingStory.member.entity.MemberEntity;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class MemberFileDto {

    private Long id;

    private String memberNewFileName;

    private String memberOldFileName;

    private MemberEntity memberEntity;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

}
