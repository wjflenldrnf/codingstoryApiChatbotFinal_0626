package org.spring.codingStory.approval.controller;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.spring.codingStory.approval.dto.ApprovalDivDto;
import org.spring.codingStory.approval.dto.ApprovalDto;
import org.spring.codingStory.approval.entity.ApprovalDivEntity;
import org.spring.codingStory.approval.entity.ApprovalEntity;
import org.spring.codingStory.approval.entity.ApprovalStatusEntity;
import org.spring.codingStory.approval.serviceImpl.ApprovalServiceImpl;
import org.spring.codingStory.approval.serviceImpl.service.ApprovalService;
import org.spring.codingStory.config.MyUserDetails;
import org.spring.codingStory.member.dto.MemberDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.Param;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    return "apv/write";
    }

    @PostMapping("/write")
    public String write(ApprovalDto approvalDto) throws IOException {

        approvalService.apvWrite(approvalDto);

        return "/index";
    }



    @GetMapping("/list")
    public String apvList(Model model, ApprovalEntity approvalEntity,
                          ApprovalStatusEntity approvalStatusEntity,
                          ApprovalDivEntity approvalDivEntity,
                          @AuthenticationPrincipal MyUserDetails myUserDetails,
                          @RequestParam(name = "subject", required = false) String subject,
                          @RequestParam(name = "search", required = false) String search,
                          @PageableDefault(page = 0, size = 8, sort = "board_id", direction = Sort.Direction.DESC)
                              Pageable pageable){

        model.addAttribute("approvalEntity",approvalEntity);
        model.addAttribute("approvalStatusEntity",approvalStatusEntity);
        model.addAttribute("approvalDivEntity",approvalDivEntity);
        model.addAttribute("myUserDetails",myUserDetails);

        Page<ApprovalDto> approvalDtoPage = approvalService.apvList(pageable, subject, search);

        int totalPages = approvalDtoPage.getTotalPages();
        int newPage = approvalDtoPage.getNumber();
        int blockNum = 10;
        int startPage = (int) (
            (Math.floor(newPage / blockNum) * blockNum) + 1 <=
                totalPages ? (Math.floor(newPage / blockNum) * blockNum) + 1 : totalPages);
        int endPage = (startPage + blockNum) - 1 < totalPages ? (startPage + blockNum) - 1 : totalPages;
        model.addAttribute("startPage", startPage);
        model.addAttribute("newPage", newPage);
        model.addAttribute("endPage", endPage);
        model.addAttribute("approvalDtoPage", approvalDtoPage);


        return "/apv/list";
    }



}
