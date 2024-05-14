package org.spring.codingStory.attendance;

import lombok.RequiredArgsConstructor;
import org.spring.codingStory.attendance.attendanceServiceImpl.attendanceService.AttendanceService;
import org.spring.codingStory.attendance.dto.AttendanceDto;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping("/att")
public class AttendanceController {

    private final AttendanceService attendanceService;


//    @GetMapping({"", "/", "/index"})
//    public String index() {
//
//        return "att/attIndex";
//    }

    @GetMapping({"", "/", "/index"})
    public String attList(
            @RequestParam(value = "id", required = false) Long id,
            Model model,
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            @RequestParam(required = false) LocalDate date){

        AttendanceDto.StatusAndIndexWithPage statusAndList = new AttendanceDto.StatusAndIndexWithPage();
        List<AttendanceDto.Index> attendanceDtoList = null;
        attendanceDtoList = attendanceService.findAllById(id);
//        statusAndList.setStatus(attendanceService.findAllOnlyStatus());
        statusAndList.setAttendanceList(attendanceDtoList);

        model.addAttribute("statusAndList", statusAndList);

        return "att/attIndex";
    }


}
