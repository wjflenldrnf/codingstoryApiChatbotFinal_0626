package org.spring.codingStory.attendance.controller.api;

import lombok.RequiredArgsConstructor;
import org.spring.codingStory.attendance.dto.AttendanceDto;
import org.spring.codingStory.attendance.entity.AttendanceEntity;
import org.spring.codingStory.attendance.serviceImpl.AttendanceServiceImpl;
import org.spring.codingStory.config.MyUserDetails;
import org.spring.codingStory.member.dto.MemberDto;
import org.spring.codingStory.member.serviceImpl.MemberServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
@Transactional
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin")
public class AttendanceController {

    private final AttendanceServiceImpl attendanceServiceImpl;
    private final MemberServiceImpl memberServiceImpl;

    @ResponseBody
    @GetMapping("/attendance/attList")
    public ResponseEntity<List<AttendanceDto>> attList() {
        List<AttendanceDto> attendanceDtoList = attendanceServiceImpl.attList();
        return new ResponseEntity<>(attendanceDtoList, HttpStatus.OK);
    }

    @PostMapping("/attendance/checkInTime")
    public ResponseEntity<Integer> checkInTimeInsert(@RequestBody AttendanceDto attendanceDto){

        if (attendanceServiceImpl.hasAttendanceToday(attendanceDto.getMemberId())) {
            throw new IllegalArgumentException("already checked");
        }


        int checkInTime= attendanceServiceImpl.insertCheckInAttendance(attendanceDto);
        return new ResponseEntity<>(checkInTime,HttpStatus.OK);
    }


    @ResponseBody
    @PostMapping("/attendance/attendanceDelete/{id}/member/{memberId}")
    public ResponseEntity<?> attendanceDelete(@PathVariable("id")Long id, @PathVariable("memberId")Long memberId) {

        int result= attendanceServiceImpl.attendanceDelete(id);

        return ResponseEntity.status(HttpStatus.OK).body(result);
    }


    // 퇴근 엔드포인트 추가
//    @ResponseBody
    @PutMapping("/attendance/{id}")
    public ResponseEntity<?> checkOutTimeInsert(@PathVariable("id") Long id,
                                                @AuthenticationPrincipal MyUserDetails myUserDetails,
                                                @RequestBody AttendanceDto attendanceDto) {

        Long memberId = myUserDetails.getMemberEntity().getId();
        int result = attendanceServiceImpl.updateCheckOutAttendance(memberId, id, attendanceDto);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

}