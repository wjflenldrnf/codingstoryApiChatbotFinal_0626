package org.spring.codingStory.attendance.attendanceServiceImpl;

import lombok.RequiredArgsConstructor;
import org.spring.codingStory.attendance.attendanceServiceImpl.attendanceService.AttendanceService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class AttendanceServiceImpl implements AttendanceService{

}
