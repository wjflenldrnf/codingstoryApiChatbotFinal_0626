package org.spring.codingStory.attendance.serviceImpl.service;

import org.spring.codingStory.attendance.dto.AttendanceDto;

import java.util.List;

public interface AttendanceService {

    void insertCheckInAttendance(AttendanceDto attendanceDto);

    List<AttendanceDto> attList();

    Integer insertCheckInAttendance2(AttendanceDto attendanceDto);


    int attendanceDelete(Long id);

    int attendanceUpdate(Long id, AttendanceDto attendanceDto);
}
