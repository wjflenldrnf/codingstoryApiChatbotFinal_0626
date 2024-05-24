package org.spring.codingStory.pay.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.spring.codingStory.attendance.entity.AttendanceEntity;
import org.spring.codingStory.contraint.BaseTimeEntity;
import org.spring.codingStory.member.entity.MemberEntity;
import org.spring.codingStory.pay.dto.PayDto;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "pay")
public class PayEntity extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pay_id")
    private Long id;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private MemberEntity memberEntity;


    @Column
    private String payMon;

    @Column
    private String payBns;

    @Column
    private LocalDate paymentDate;



    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date startTime;

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date endTime;

//    // toInsertPayEntity3 메서드
//    public static PayEntity toInsertPayEntity3(PayDto payDto, BigDecimal dailyWage, LocalDate checkOutDate) {
//        PayEntity payEntity = new PayEntity();
//        payEntity.setMemberEntity(payDto.getMemberEntity());
//
//        // 당월 1일부터 체크아웃 날짜까지의 일수 계산
//        LocalDate firstDayOfMonth = checkOutDate.withDayOfMonth(1);
//        long daysWorked = java.time.temporal.ChronoUnit.DAYS.between(firstDayOfMonth, checkOutDate) + 1;
//
//        // PayMon 설정
//        BigDecimal payMon = dailyWage.multiply(BigDecimal.valueOf(daysWorked));
//        payEntity.setPayMon(String.valueOf(payMon));
//
//        payEntity.setPayBns(payDto.getPayBns());
//        payEntity.setPaymentDate(payDto.getPaymentDate());
//        return payEntity;
//    }


    public static PayEntity toInsertPayEntity2(PayDto payDto, BigDecimal dailyWage) {
        PayEntity payEntity = new PayEntity();
        payEntity.setMemberEntity(payDto.getMemberEntity());
//        payEntity.setPayMon(String.valueOf(dailyWage.multiply(BigDecimal.valueOf(22)))); // PayMon 설정
        payEntity.setPayMon("123123"); // PayMon 설정
        payEntity.setPayBns(payDto.getPayBns());
        payEntity.setPaymentDate(payDto.getPaymentDate());
        return payEntity;
    }


    public static PayEntity toInsertPayEntity(PayDto payDto) {

        PayEntity payEntity = new PayEntity();
        payEntity.setMemberEntity(payDto.getMemberEntity());
        payEntity.setPayMon(payDto.getPayMon());
        payEntity.setPayBns(payDto.getPayBns());
        payEntity.setPaymentDate(payDto.getPaymentDate());
        return payEntity;
    }

//    public static PayEntity toInsertPayEntity(PayDto payDto) {
//
//        PayEntity payEntity = new PayEntity();
//
//
//        payEntity.setMemberEntity(payDto.getMemberEntity());
//
//        payEntity.setPayMon(payDto.getPayMon());
//
//        payEntity.setPayBns(payDto.getPayBns());
//
//        payEntity.setPaymentDate(payDto.getPaymentDate());
//        payEntity.setStartTime(payDto.getStartTime());
//        payEntity.setEndTime(payDto.getEndTime());
//
//        return payEntity;
//    }
}
