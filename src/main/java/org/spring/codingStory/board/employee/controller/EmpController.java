package org.spring.codingStory.board.employee.controller;

import lombok.RequiredArgsConstructor;
import org.spring.codingStory.board.employee.dto.EmployeeDto;
import org.spring.codingStory.board.employee.serviceImpl.EmployeeServiceImpl;
import org.spring.codingStory.config.MyUserDetails;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;

@RequiredArgsConstructor
@Controller
@RequestMapping(value = "/board")
public class EmpController {

    private final EmployeeServiceImpl employeeService;

    @GetMapping("/empWrite")
    public String employeeWrite(@AuthenticationPrincipal MyUserDetails myUserDetails,
                             EmployeeDto empDto, Model model) {

        model.addAttribute("memberId", myUserDetails.getMemberEntity().getId());
        model.addAttribute("empDto", empDto);
        model.addAttribute("memberName", myUserDetails.getMemberEntity().getName());

        return "board/empWrite";
    }


    @PostMapping("/empWrite")
    public String empWriteOK(EmployeeDto employeeDto,
                               @AuthenticationPrincipal MyUserDetails myUserDetails,
                               Model model) throws IOException {

        employeeService.empInsertFile(employeeDto);

        return "redirect:/board/empList";
        //글 작성후에 employeeList 페이지로 이동
    }
    
    
}
