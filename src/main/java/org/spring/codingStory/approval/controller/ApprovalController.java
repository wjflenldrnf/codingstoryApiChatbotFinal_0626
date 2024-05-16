package org.spring.codingStory.approval.controller;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.spring.codingStory.approval.dto.ApprovalDto;
import org.spring.codingStory.approval.serviceImpl.ApprovalServiceImpl;
import org.spring.codingStory.approval.serviceImpl.service.ApprovalService;
import org.spring.codingStory.config.MyUserDetails;
import org.spring.codingStory.member.dto.MemberDto;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;


@Controller
@RequiredArgsConstructor
@RequestMapping("/apv")
public class ApprovalController {

    private final ApprovalServiceImpl approvalService;

//    먼저 리스트를 뽑아내야한다(대기문서,완료, 전체)

    @GetMapping("/write")
    public String write(@AuthenticationPrincipal MyUserDetails myUserDetails, Model model,ApprovalDto approvalDto ){

        model.addAttribute("memberId", myUserDetails.getMemberEntity().getId());
        model.addAttribute("memberName",myUserDetails.getMemberEntity().getName());
        model.addAttribute("approvalDto",approvalDto.getApvAttachFile());

    return "/apv/write";
    }

    @PostMapping("/write")
    public String write(ApprovalDto approvalDto) throws IOException {

        approvalService.apvWrite(approvalDto);

        return "/index";
    }



    @GetMapping("/detail")
    public String detail(Model model, ApprovalDto approvalDto){

        return "/apv/detail";
    }



}
