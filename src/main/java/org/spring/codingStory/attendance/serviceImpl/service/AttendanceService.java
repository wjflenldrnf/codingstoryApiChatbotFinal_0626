package org.spring.codingStory.attendance.serviceImpl.service;

import org.spring.codingStory.attendance.dto.AttendanceDto;


import java.util.List;

public interface AttendanceService {



    List<AttendanceDto> attList();

    Integer insertCheckInAttendance(AttendanceDto attendanceDto);
    Integer insertCheckInAttendance2(AttendanceDto attendanceDto);


    int attendanceDelete(Long id);

    int updateCheckOutAttendance(Long id, AttendanceDto attendanceDto);

    boolean hasAttendanceToday(Long memberId);

    //////////////////////////////////////////
    AttendanceDto getAttendanceById(Long id);
}