package org.spring.codingStory.controller;

import lombok.RequiredArgsConstructor;
import org.spring.codingStory.approval.serviceImpl.ApprovalServiceImpl;
import org.spring.codingStory.board.employee.dto.EmployeeDto;
import org.spring.codingStory.board.employee.serviceImpl.EmployeeServiceImpl;
import org.spring.codingStory.board.freeBoard.dto.FreeDto;
import org.spring.codingStory.board.freeBoard.serviceImpl.FreeServiceImpl;
import org.spring.codingStory.board.notice.dto.NoticeDto;
import org.spring.codingStory.board.notice.serviceImpl.NoticeServiceImpl;
import org.spring.codingStory.config.MyUserDetails;
import org.spring.codingStory.pay.dto.PayDto;
import org.spring.codingStory.pay.serviceImpl.PayServiceImpl;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class HomeController {

    private final FreeServiceImpl freeService;
    private final EmployeeServiceImpl employeeService;
    private final NoticeServiceImpl noticeService;
    private final PayServiceImpl payServiceImpl;
    private final ApprovalServiceImpl approvalService;



    @GetMapping({"","/login"})
    public String login(@RequestParam(value="error", required = false) String error,
                        @RequestParam(value = "exception", required = false) String exception,
                        Model model){

        model.addAttribute("error",error);
        model.addAttribute("exception",exception);


        return "login";
    }

    @GetMapping("/index")
    public String index(@AuthenticationPrincipal MyUserDetails myUserDetails, Model model) {

        List<FreeDto> freeHit=freeService.freeHit();
        List<EmployeeDto> empHit=employeeService.empHit();
        List<NoticeDto> noticeHit=noticeService.noticeHit();


        model.addAttribute("myUserDetails", myUserDetails);
        model.addAttribute("freeHit",freeHit);
        model.addAttribute("empHit",empHit);
        model.addAttribute("noticeHit",noticeHit);

        model.addAttribute("name" ,myUserDetails.getMemberEntity().getName());
        model.addAttribute("memberId",myUserDetails.getMemberEntity().getId());

        List<PayDto> payList = payServiceImpl.findByMemberId(myUserDetails.getMemberEntity().getId());
       /* model.addAttribute("pay", payList.get(payList.size() - 1));*/

        String name= myUserDetails.getName();
        Long memberId = myUserDetails.getMemberEntity().getId();
        Long apvWaitCount = approvalService.apvWaitCount(name,1L);// 대기중인 문서 수
        Long apvDenyCount = approvalService.apvDenyCount(name,3L);// 내가 반려한 문서 수
        Long apvMyCount = approvalService.apvMyCount(memberId);// 내가 보낸 문서 수
        Long apvMyDenyCount = approvalService.apvMyDenyCount(memberId,3L); // 내가 보낸 문서 중 반려된 문서 수
        Long apvCount=approvalService.apvCount(name);

        model.addAttribute("apvWaitCount",apvWaitCount);
        model.addAttribute("apvDenyCount",apvDenyCount);
        model.addAttribute("apvMyCount",apvMyCount);
        model.addAttribute("apvMyDenyCount",apvMyDenyCount);
        model.addAttribute("apvCount",apvCount);

        return "index";
    }

    @GetMapping("/map")
    public String map() {
        return "map";
    }
    @GetMapping("/map1")
    public String map1() {
        return "map1";
    }
    @GetMapping("/map2")
    public String map2() {
        return "map2";
    }   @GetMapping("/map3")
    public String map3() {
        return "map3";
    }   @GetMapping("/map4")
    public String map4() {
        return "map4";
    }





}