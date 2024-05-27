package org.spring.codingStory.pay.serviceImpl;

import lombok.RequiredArgsConstructor;
import org.spring.codingStory.attendance.entity.AttendanceEntity;
import org.spring.codingStory.attendance.repository.AttendanceRepository;
import org.spring.codingStory.member.entity.MemberEntity;
import org.spring.codingStory.pay.dto.PayDto;
import org.spring.codingStory.pay.entity.PayEntity;
import org.spring.codingStory.pay.repository.PayRepository;
import org.spring.codingStory.pay.serviceImpl.service.PayService;
import org.spring.codingStory.payment.entity.PaymentEntity;
import org.spring.codingStory.payment.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Time;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Transactional
@Service
@RequiredArgsConstructor
public class PayServiceImpl implements PayService {


    @Autowired
    private final AttendanceRepository attendanceRepository;

    @Autowired
    private PayRepository payRepository;

    @Autowired
    private PaymentRepository paymentRepository;

    @Transactional
    public void insertPay(PayDto payDto) {
        // Date를 LocalDateTime으로 변환
        LocalDateTime start = convertToLocalDateTime(payDto.getStartDate());
        LocalDateTime end = convertToLocalDateTime(payDto.getEndDate());

        Long totalWorkTimeInSeconds = attendanceRepository.findTotalWorkTimeByMemberIdAndCheckInTimeBetween(
                payDto.getMemberId(), start, end);

        // 총 작업 시간을 초 단위에서 Time 형식으로 변환
        Time calcTime = convertSecondsToTime(totalWorkTimeInSeconds);

        // PaymentEntity의 hourlyWage를 가져오기
        PaymentEntity paymentEntity = paymentRepository.findByMemberEntity_Id(payDto.getMemberId());
        Double hourlyWage = Double.parseDouble(paymentEntity.getHourlyWage());

        // 총 작업 시간(초)을 시간 단위로 변환
        double totalWorkHours = totalWorkTimeInSeconds / 3600.0;

        // payInDur 계산
        Double payInDur = totalWorkHours * hourlyWage;

        // payBns 가져오기
        Double payBns = payDto.getPayBns();

        // totalPay 계산
        Double totalPay = payInDur + payBns;

        payDto.setMemberEntity(MemberEntity.builder()
                .id(payDto.getMemberId())
                .build());
        payDto.setPayInDur(payInDur);
        payDto.setCalcTime(calcTime);
        payDto.setTotalPay(totalPay);

        PayEntity payEntity = PayEntity.toInsertPayEntity(payDto, calcTime);
        payRepository.save(payEntity);
    }


//    @Transactional
//    public void insertPay(PayDto payDto) {
//        // Date를 LocalDateTime으로 변환
//        LocalDateTime start = convertToLocalDateTime(payDto.getStartDate());
//        LocalDateTime end = convertToLocalDateTime(payDto.getEndDate());
//
//        Long totalWorkTimeInSeconds = attendanceRepository.findTotalWorkTimeByMemberIdAndCheckInTimeBetween(
//                payDto.getMemberId(), start, end);
//
//        // 총 작업 시간을 초 단위에서 Time 형식으로 변환
//        Time calcTime = convertSecondsToTime(totalWorkTimeInSeconds);
//
//        // PaymentEntity의 hourlyWage를 가져오기
//        PaymentEntity paymentEntity = paymentRepository.findByMemberEntity_Id(payDto.getMemberId());
//        Double hourlyWage = Double.parseDouble(paymentEntity.getHourlyWage());
//
//        // 총 작업 시간(초)을 시간 단위로 변환
//        double totalWorkHours = totalWorkTimeInSeconds / 3600.0;
//
//        // payInDur 계산
//        Double payInDur = totalWorkHours * hourlyWage;
//
//        payDto.setMemberEntity(MemberEntity.builder()
//                .id(payDto.getMemberId())
//                .build());
//        payDto.setPayInDur(payInDur);
//        payDto.setCalcTime(calcTime);
//
//        PayEntity payEntity = PayEntity.toInsertPayEntity(payDto, calcTime);
//        payRepository.save(payEntity);
//    }


//    @Autowired
//    private final PayRepository payRepository;
//    @Autowired
//    private final AttendanceRepository attendanceRepository;
//
//
//    @Transactional
//    @Override
//    public void insertPay(PayDto payDto) {
//        // Date를 LocalDateTime으로 변환
//        LocalDateTime start = convertToLocalDateTime(payDto.getStartDate());
//        LocalDateTime end = convertToLocalDateTime(payDto.getEndDate());
//
//        Long totalWorkTimeInSeconds = attendanceRepository.findTotalWorkTimeByMemberIdAndCheckInTimeBetween(
//                payDto.getMemberId(), start, end);
//
//        // 총 작업 시간을 초 단위에서 Time 형식으로 변환
//        Time calcTime = convertSecondsToTime(totalWorkTimeInSeconds);
//
//        payDto.setMemberEntity(MemberEntity.builder()
//                .id(payDto.getMemberId())
//                .build());
//
//        PayEntity payEntity = PayEntity.toInsertPayEntity(payDto, calcTime);
//        payRepository.save(payEntity);
//    }

    private LocalDateTime convertToLocalDateTime(Date date) {
        return date.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();
    }

    private Time convertSecondsToTime(Long totalSeconds) {
        long hours = totalSeconds / 3600;
        long minutes = (totalSeconds % 3600) / 60;
        long seconds = totalSeconds % 60;
        return Time.valueOf(String.format("%02d:%02d:%02d", hours, minutes, seconds));
    }

}
