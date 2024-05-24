package org.spring.codingStory.pay.dto;

import lombok.*;
import org.spring.codingStory.member.entity.MemberEntity;
import org.spring.codingStory.pay.entity.PayEntity;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;


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

    //지급날짜?
    private LocalDate paymentDate;


    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private Date startTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private Date endTime;

    private Long memberId;

    public static PayDto toSeletAllPayDto(PayEntity payEntity) {

        PayDto payDto=new PayDto();
        payDto.setId(payEntity.getId());
        payDto.setMemberEntity(payEntity.getMemberEntity());
        payDto.setPayMon(payEntity.getPayMon());
        payDto.setPayBns(payEntity.getPayBns());

        return payDto;
    }
}
