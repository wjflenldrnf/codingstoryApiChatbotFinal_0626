package org.spring.codingStory.attendance.dto;

import lombok.*;
import org.spring.codingStory.attendance.entity.AttendanceEntity;
import org.spring.codingStory.member.entity.MemberEntity;
import org.springframework.format.annotation.DateTimeFormat;

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

//    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
//    private Date checkInTime;
//    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
//    private Date checkOutTime;

    private LocalDateTime checkInTime;

    private LocalDateTime checkOutTime;

    private String attendanceType;

    private Long memberId;


    //entity -> dto
    public static AttendanceDto toUpdateAttendanceDto(AttendanceEntity attendanceEntity) {
        AttendanceDto attendanceDto=new AttendanceDto();

        attendanceDto.setId(attendanceEntity.getId());
        attendanceDto.setMemberEntity(attendanceEntity.getMemberEntity());
        attendanceDto.setCheckInTime(attendanceEntity.getCheckInTime());
        attendanceDto.setCheckOutTime(attendanceEntity.getCheckOutTime());
//        attendanceDto.setAttendanceType(attendanceDto.getAttendanceType());
        attendanceDto.setAttendanceType(attendanceEntity.getAttendanceType());

        return attendanceDto;
    }

}

//public static AttendanceDto toInsertAttendanceDto(AttendanceEntity attendanceEntity){
//
//    return
//}