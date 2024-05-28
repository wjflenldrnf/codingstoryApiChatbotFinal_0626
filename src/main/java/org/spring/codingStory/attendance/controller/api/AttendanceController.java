package org.spring.codingStory.attendance.controller.api;

import lombok.RequiredArgsConstructor;
import org.spring.codingStory.attendance.dto.AttendanceDto;
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
import java.util.List;
@Transactional
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin")
public class AttendanceController {

    private final AttendanceServiceImpl attendanceServiceImpl;
    private final MemberServiceImpl memberServiceImpl;

    @PostMapping("/attendance/checkInTime")
    public ResponseEntity<Integer> checkInTimeInsert(@RequestBody AttendanceDto attendanceDto){

        if (attendanceServiceImpl.hasAttendanceToday(attendanceDto.getMemberId())) {
            throw new IllegalArgumentException("already checked");
        }
        int checkInTime= attendanceServiceImpl.insertCheckInAttendance(attendanceDto);
        return new ResponseEntity<>(checkInTime,HttpStatus.OK);
    }

    // 퇴근 엔드포인트 추가
    @PutMapping("/attendance/{id}")
    public ResponseEntity<?> checkOutTimeInsert(@PathVariable("id") Long id, @RequestBody AttendanceDto attendanceDto) {
        int result = attendanceServiceImpl.updateCheckOutAttendance(id, attendanceDto);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @ResponseBody
    @GetMapping("/attendance/attList")
    public ResponseEntity<List<AttendanceDto>> attList() {
        List<AttendanceDto> attendanceDtoList = attendanceServiceImpl.attList();
        return new ResponseEntity<>(attendanceDtoList, HttpStatus.OK);
    }

    @ResponseBody
    @PostMapping("/attendance/attendanceDelete/{id}/member/{memberId}")
    public ResponseEntity<?> attendanceDelete(@PathVariable("id")Long id, @PathVariable("memberId")Long memberId) {

        int result= attendanceServiceImpl.attendanceDelete(id);

        return ResponseEntity.status(HttpStatus.OK).body(result);
    }


    ////////////////////////////////////////////////////////////////////////////

    @PostMapping("/attendance/test/checkInTime")
    public ResponseEntity<AttendanceDto> checkInTimeInsert2(@RequestBody AttendanceDto attendanceDto) {
        if (attendanceServiceImpl.hasAttendanceToday(attendanceDto.getMemberId())) {
            throw new IllegalArgumentException("already checked");
        }

        int checkInTime = attendanceServiceImpl.insertCheckInAttendance2(attendanceDto);
        return new ResponseEntity<>(attendanceDto, HttpStatus.OK); // 생성된 attendance 객체를 반환
    }

    @PutMapping("/attendance/test/{id}")
    public ResponseEntity<AttendanceDto> checkOutTimeInsert2(@PathVariable("id") Long id, @RequestBody AttendanceDto attendanceDto) {
        attendanceServiceImpl.updateCheckOutAttendance(id, attendanceDto);
        AttendanceDto updatedAttendance = attendanceServiceImpl.getAttendanceById(id); // 업데이트된 attendance 정보 가져오기
        return ResponseEntity.status(HttpStatus.OK).body(updatedAttendance);
    }


}