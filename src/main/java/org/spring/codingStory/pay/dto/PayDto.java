package org.spring.codingStory.pay.dto;

import lombok.*;
import org.spring.codingStory.member.entity.MemberEntity;
import org.spring.codingStory.pay.entity.PayEntity;
import org.spring.codingStory.payment.entity.PaymentEntity;
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

    private PaymentEntity paymentEntity;
    private Long paymentId;

    public static PayDto toSelectOnePayDtoList(PayEntity payEntity) {
        PayDto payDto = new PayDto();
        payDto.setId(payEntity.getId());
        payDto.setMemberEntity(payEntity.getMemberEntity());

        payDto.setMemberId(payEntity.getMemberEntity().getId()); // 추가된 부분

        payDto.setPaymentEntity(payEntity.getPaymentEntity());
        payDto.setStartDate(payEntity.getStartDate());
        payDto.setEndDate(payEntity.getEndDate());
        payDto.setCalcTime(payEntity.getCalcTime());
        payDto.setPayInDur(payEntity.getPayInDur());
        payDto.setPayBns(payEntity.getPayBns());
        payDto.setTotalPay(payEntity.getTotalPay());
        payDto.setPayingDate(payEntity.getPayingDate());
        return payDto;
    }

}
