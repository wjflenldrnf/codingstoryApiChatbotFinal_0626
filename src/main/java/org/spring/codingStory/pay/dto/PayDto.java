package org.spring.codingStory.pay.dto;

import lombok.*;
import org.spring.codingStory.member.entity.MemberEntity;

import java.time.LocalDate;
import java.time.LocalDateTime;


@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class PayDto {

    private Long id;

    private MemberEntity memberEntity;

    private String payMon;

    private String payBns;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    private LocalDate paymentDate;
}
