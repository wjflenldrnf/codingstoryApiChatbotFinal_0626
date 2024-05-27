package org.spring.codingStory.attendance.serviceImpl.service;

import org.spring.codingStory.attendance.dto.AttendanceDto;
import org.spring.codingStory.attendance.entity.AttendanceEntity;

import java.sql.Time;
import java.time.LocalDateTime;
import java.util.List;

public interface AttendanceService {



    List<AttendanceDto> attList();

    Integer insertCheckInAttendance(AttendanceDto attendanceDto);


    int attendanceDelete(Long id);

    int updateCheckOutAttendance(Long memberId, Long id, AttendanceDto attendanceDto);

    boolean hasAttendanceToday(Long memberId);


//    Time calculationSetWorkTime(LocalDateTime checkInTime, LocalDateTime checkOutTime);
}
