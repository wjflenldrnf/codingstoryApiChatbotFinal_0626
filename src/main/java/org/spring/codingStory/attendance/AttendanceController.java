package org.spring.codingStory.attendance;

import lombok.RequiredArgsConstructor;
import org.spring.codingStory.attendance.attendanceServiceImpl.AttendanceServiceImpl;
import org.spring.codingStory.attendance.dto.AttendanceDto;
import org.spring.codingStory.config.MyUserDetails;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;

@Controller
@RequiredArgsConstructor
@RequestMapping("/attendance")
public class AttendanceController {

    private final AttendanceServiceImpl attendanceServiceImpl;


    @GetMapping({"/",""})
    public String attWrite(AttendanceDto attendanceDto, Model model,
                           @AuthenticationPrincipal MyUserDetails myUserDetails){

        model.addAttribute("memberId", myUserDetails.getMemberEntity().getId());

        return "attendance/attIndex";
    }


    @PostMapping({"/",""})
    public String writeOk(AttendanceDto attendanceDto) throws IOException {

        attendanceServiceImpl.insertCheckInAttendance(attendanceDto);
        return "redirect:/attendance/attIndex";

    }



}