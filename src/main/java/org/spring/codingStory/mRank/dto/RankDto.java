package org.spring.codingStory.mRank.dto;


import lombok.*;
import org.spring.codingStory.member.entity.MemberEntity;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class RankDto {

    private Long id;

    private String rankName;

    private List<MemberEntity> memberEntityList;
}
