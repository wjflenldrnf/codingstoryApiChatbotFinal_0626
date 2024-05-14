package org.spring.codingStory.attendance;

import lombok.RequiredArgsConstructor;
import org.spring.codingStory.attendance.attendanceServiceImpl.attendanceService.AttendanceService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@Controller
@RequestMapping("/att")
public class AttendanceController {

    private final AttendanceService attendanceService;


    @GetMapping({"", "/", "/index"})
    public String index() {

        return "att/attIndex";
    }




}
