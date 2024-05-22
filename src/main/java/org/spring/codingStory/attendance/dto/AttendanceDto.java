package org.spring.codingStory.attendance.dto;

import lombok.*;
import org.spring.codingStory.attendance.entity.AttendanceEntity;
import org.spring.codingStory.member.entity.MemberEntity;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import java.math.BigDecimal;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class AttendanceDto {

    private Long id;

    private MemberEntity memberEntity;

    private LocalDateTime checkInTime;

    private LocalDateTime checkOutTime;

    private String attendanceType;

    private Long memberId;




    private Time workTime;


    private BigDecimal dailyWage;
//
//
//    private LocalDate workDay;
//
//
//    private BigDecimal weeklyAllowance;
//
//
//    private BigDecimal bonus;

    private BigDecimal hourWage;




    //entity -> dto
    public static AttendanceDto toSelectAllAttendanceDto(AttendanceEntity attendanceEntity) {
        AttendanceDto attendanceDto=new AttendanceDto();

        attendanceDto.setId(attendanceEntity.getId());
        attendanceDto.setMemberEntity(attendanceEntity.getMemberEntity());
        attendanceDto.setCheckInTime(attendanceEntity.getCheckInTime());
        attendanceDto.setCheckOutTime(attendanceEntity.getCheckOutTime());
        attendanceDto.setAttendanceType(attendanceEntity.getAttendanceType());
        attendanceDto.setWorkTime(attendanceEntity.getWorkTime());

        return attendanceDto;
    }

}
