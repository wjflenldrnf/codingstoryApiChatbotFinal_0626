package org.spring.codingStory.pay.controller;

import lombok.RequiredArgsConstructor;
import org.spring.codingStory.attendance.dto.AttendanceDto;
import org.spring.codingStory.attendance.entity.AttendanceEntity;
import org.spring.codingStory.attendance.serviceImpl.AttendanceServiceImpl;
import org.spring.codingStory.config.MyUserDetails;
import org.spring.codingStory.pay.dto.PayDto;
import org.spring.codingStory.pay.serviceImpl.PayServiceImpl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/pay")
@Controller
public class PayController {

    private final PayServiceImpl payServiceImpl;
    private final AttendanceServiceImpl attendanceService;

    @GetMapping({"/",""})
    public String payIndex(@AuthenticationPrincipal MyUserDetails myUserDetails, Model model){


        model.addAttribute("name" ,myUserDetails.getMemberEntity().getName());
        model.addAttribute("memberId",myUserDetails.getMemberEntity().getId());
        return "pay/payIndex";
//        return "redirect:/attendance/attDetail";
//        return "redirect:/";
    }

    //////////////////////////////////////////////////////////

    @GetMapping("/payWrite")
    public String write(PayDto payDto
            , @AuthenticationPrincipal MyUserDetails myUserDetails
            , Model model) {
        model.addAttribute("memberId", myUserDetails.getMemberEntity().getId());
//        return "pay/write";
        return "pay/payWrite";
    }


    @PostMapping("/payWrite")
    public String writeOk(@Valid PayDto payDto,
                          BindingResult bindingResult) throws IOException {

        if (bindingResult.hasErrors()) {
//            return "pay/write";
            return "pay/payWrite";
        }

        payServiceImpl.insertPay(payDto);
        return "redirect:/pay";
    }
    //////////////////////////////////////////////////////////////

    //////////////////////////////////////////////////////////////

    @GetMapping("/payList")
    public String payList(
            @RequestParam(name = "subject", required = false) String subject,
            @RequestParam(name = "search", required = false) String search,
            @AuthenticationPrincipal MyUserDetails myUserDetails,
            @PageableDefault(page = 0, size = 5, sort = "id", direction = Sort.Direction.DESC) Pageable pageable,
            Model model) {

        // 월별 월급 계산을 위해 출근 기록을 가져옴

        Long memberId = 2L;

        // 해당 월의 월급을 계산
        BigDecimal monthlySalary = attendanceService.calculateMonthlySalaryForMonth(memberId);

        // 페이징된 payDto 목록 가져오기
        Page<PayDto> pagingList = payServiceImpl.paySearchPagingList(pageable, subject, search);

        int totalPages = pagingList.getTotalPages(); // 전체 페이지
        int nowPage = pagingList.getNumber(); // 현재 페이지
        long totalElements = pagingList.getTotalElements(); // 전체 레코드 개수
        int size = pagingList.getSize(); // 페이지 당 보이는 개수

        int blockNum = 3; // 브라우저에 보이는 페이지 번호

        int startPage = (int) ((Math.floor(nowPage / blockNum) * blockNum) + 1 <= totalPages ? (Math.floor(nowPage / blockNum) * blockNum) + 1 : totalPages);
        int endPage = (startPage + blockNum) - 1 < totalPages ? (startPage + blockNum) - 1 : totalPages;

        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        model.addAttribute("pagingList", pagingList);
        model.addAttribute("monthlySalary", monthlySalary);

        return "pay/payList"; // payList 페이지로 이동
    }
    

}
