package org.spring.codingStory.attendance.controller.api;

import lombok.RequiredArgsConstructor;
import org.spring.codingStory.attendance.dto.AttendanceDto;
import org.spring.codingStory.attendance.serviceImpl.AttendanceServiceImpl;
import org.spring.codingStory.config.MyUserDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin")
public class AttendanceController {

    private final AttendanceServiceImpl attendanceServiceImpl;

    @ResponseBody
    @GetMapping("/attendance/attList")
    public ResponseEntity<List<AttendanceDto>> attList() {
        List<AttendanceDto> attendanceDtoList = attendanceServiceImpl.attList();
        return new ResponseEntity<>(attendanceDtoList, HttpStatus.OK);
    }

    @PostMapping("/attendance/checkInTime")
    public ResponseEntity<Integer> checkInTimeInsert(@RequestBody AttendanceDto attendanceDto){
        int checkInTime= attendanceServiceImpl.insertCheckInAttendance2(attendanceDto);
        return new ResponseEntity<>(checkInTime,HttpStatus.OK);
    }

}