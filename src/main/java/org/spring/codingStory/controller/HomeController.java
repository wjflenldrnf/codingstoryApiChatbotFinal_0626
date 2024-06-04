package org.spring.codingStory.controller;

import lombok.RequiredArgsConstructor;
import org.spring.codingStory.board.freeBoard.dto.FreeDto;
import org.spring.codingStory.board.freeBoard.serviceImpl.FreeServiceImpl;
import org.spring.codingStory.config.MyUserDetails;
import org.spring.codingStory.department.dto.DepartmentDto;
import org.spring.codingStory.department.entity.DepartmentEntity;
import org.spring.codingStory.department.serviceimpl.service.DepartmentService;
import org.spring.codingStory.fullcalender.dto.FullCalenderDto;
import org.spring.codingStory.fullcalender.serviceImpl.service.FullCalenderService;
import org.spring.codingStory.member.dto.MemberDto;
import org.spring.codingStory.member.entity.MemberEntity;
import org.spring.codingStory.member.serviceImpl.service.MemberService;
import org.spring.codingStory.pay.dto.PayDto;
import org.spring.codingStory.pay.serviceImpl.PayServiceImpl;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.lang.reflect.Member;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Controller
public class HomeController {

    private final FreeServiceImpl freeService;
    private final PayServiceImpl payServiceImpl;
    private final FullCalenderService fullCalenderService;
    private final DepartmentService departmentService;
    private final MemberService memberService;


    @GetMapping({"","/login"})
    public String login(@RequestParam(value="error", required = false) String error,
                        @RequestParam(value = "exception", required = false) String exception,
                        Model model){

        model.addAttribute("error",error);
        model.addAttribute("exception",exception);


        return "login";
    }

//    @GetMapping("/index")
//    public String index(@AuthenticationPrincipal MyUserDetails myUserDetails, Model model) {
//
//        model.addAttribute("myUserDetails", myUserDetails);
//
//        return "index";
//    }

    @GetMapping("/index")
    public String index(@AuthenticationPrincipal MyUserDetails myUserDetails, Model model) {

        //개인일정 가져오기
        List<FullCalenderDto> personalEvents=fullCalenderService.getUserEvents(myUserDetails.getMemberEntity().getId());

        //전체일정 가져오기
        List<FullCalenderDto> allEvents= fullCalenderService.getAllEvents();

        List<FreeDto> freeHit=freeService.freeHit();

        //부서명
        List<DepartmentEntity> departments = departmentService.getAllDepartments();
        //부서에 해당하는 부서명
        Map<String,List<MemberDto>> members=new HashMap<>();

        for(DepartmentEntity departmentEntity:departments){
            List<MemberDto> memberList = memberService.findByDepartment(departmentEntity.getDptName());
            members.put(departmentEntity.getDptName(),memberList);
        }



        model.addAttribute("departments",departments);
        model.addAttribute("members",members);



        model.addAttribute("myUserDetails", myUserDetails);
        model.addAttribute("freeHit",freeHit);

        model.addAttribute("name" ,myUserDetails.getMemberEntity().getName());
        model.addAttribute("memberId",myUserDetails.getMemberEntity().getId());

        model.addAttribute("personalEvents", personalEvents);
        model.addAttribute("allEvents", allEvents);



        List<PayDto> payList = payServiceImpl.findByMemberId(myUserDetails.getMemberEntity().getId());
        /*model.addAttribute("pay", payList.get(payList.size() - 1));*/



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