package org.spring.codingStory.pay.dto;

import lombok.*;
import org.spring.codingStory.member.entity.MemberEntity;
import org.spring.codingStory.pay.entity.PayEntity;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.sql.Time;
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
    private Long memberId;


    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private Date startDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private Date endDate;


    /////////////////////////////////////


    private Time calcTime;

    private Double payInDur;

    private Double payBns = 0.0;

    private Double totalPay;

    private LocalDate payingDate;





//    public static PayDto toSeletAllPayDto(PayEntity payEntity) {
//
//        PayDto payDto=new PayDto();
//        payDto.setId(payEntity.getId());
//        payDto.setMemberEntity(payEntity.getMemberEntity());
//        payDto.setPayMon(payEntity.getPayMon());
//        payDto.setPayBns(payEntity.getPayBns());
//
//        return payDto;
//    }
}
