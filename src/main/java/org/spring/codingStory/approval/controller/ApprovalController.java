package org.spring.codingStory.approval.controller;

import lombok.RequiredArgsConstructor;
import org.spring.codingStory.approval.serviceImpl.service.ApprovalService;
import org.spring.codingStory.config.MyUserDetailsImpl;
import org.spring.codingStory.member.dto.MemberDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/apv")
public class ApprovalController {

    private final ApprovalService approvalService;

//    먼저 리스트를 뽑아내야한다(대기문서,완료, 전체)




    @GetMapping("/write")
    public String insert(@AuthenticationPrincipal MyUserDetailsImpl myUserDetails,
                         @ModelAttribute Model model,
                         MemberDto memberDto){

    return "/apv/write";
    }


    @PostMapping("/write")
    public String insert(@ModelAttribute Model model,
                         MemberDto memberDto){

        return "/index";
    }



}
