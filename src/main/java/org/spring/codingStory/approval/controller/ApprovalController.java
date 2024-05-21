package org.spring.codingStory.approval.controller;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.spring.codingStory.approval.dto.ApprovalDivDto;
import org.spring.codingStory.approval.dto.ApprovalDto;
import org.spring.codingStory.approval.dto.ApprovalStatusDto;
import org.spring.codingStory.approval.entity.ApprovalDivEntity;
import org.spring.codingStory.approval.entity.ApprovalEntity;
import org.spring.codingStory.approval.entity.ApprovalStatusEntity;
import org.spring.codingStory.approval.serviceImpl.ApprovalDivServiceImpl;
import org.spring.codingStory.approval.serviceImpl.ApprovalServiceImpl;
import org.spring.codingStory.approval.serviceImpl.ApprovalStatusServiceImpl;
import org.spring.codingStory.approval.serviceImpl.service.ApprovalDivService;
import org.spring.codingStory.approval.serviceImpl.service.ApprovalService;
import org.spring.codingStory.config.MyUserDetails;
import org.spring.codingStory.department.entity.DepartmentEntity;
import org.spring.codingStory.member.dto.MemberDto;
import org.spring.codingStory.member.entity.MemberEntity;
import org.spring.codingStory.member.serviceImpl.MemberServiceImpl;
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
import java.util.List;


@Controller
@RequiredArgsConstructor
@RequestMapping("/apv")
public class ApprovalController {

    private final MemberServiceImpl memberService;
    private final ApprovalServiceImpl approvalService;
    private final ApprovalDivServiceImpl approvalDivService;
    private final ApprovalStatusServiceImpl approvalStatusService;
//    먼저 리스트를 뽑아내야한다(대기문서,완료, 전체)

    @GetMapping("/write")
    public String write(@AuthenticationPrincipal MyUserDetails myUserDetails, Model model,ApprovalDto approvalDto ){
        ApprovalEntity approvalEntity=new ApprovalEntity();

        List<MemberDto> memberDtoList = memberService.memberList();
        List<ApprovalDivDto> approvalDivDtoList = approvalDivService.apvDivList();
        List<ApprovalStatusDto> approvalStatusDtoList = approvalStatusService.apvStatusList();


        model.addAttribute("myUserDetails",myUserDetails);
        model.addAttribute("memberId", myUserDetails.getMemberEntity().getId());
        model.addAttribute("memberName",myUserDetails.getMemberEntity().getName());
        model.addAttribute("memberDtoList",memberDtoList);
        model.addAttribute("approvalDivDtoList",approvalDivDtoList);
        model.addAttribute("approvalStatusDtoList",approvalStatusDtoList);
        model.addAttribute("approvalDto",approvalDto.getApvAttachFile());

        return "apv/write";
    }

    @PostMapping("/write")
    public String write(ApprovalDto approvalDto) throws IOException {

        approvalService.apvWrite(approvalDto);

        return "redirect:/apv/list";
    }

    //내가 결재자인 보고서
    @GetMapping("/list")
    public String apvList1(Model model,
                          @AuthenticationPrincipal MyUserDetails myUserDetails,
                          @RequestParam(name = "subject", required = false) String subject,
                          @RequestParam(name = "search", required = false) String search,
                          @PageableDefault(page = 0, size = 3, sort = "id", direction = Sort.Direction.DESC)
                          Pageable pageable){

        System.out.println(">>>>>>>>>"+myUserDetails.getName());
        model.addAttribute("myUserDetails",myUserDetails);
        String name = myUserDetails.getName();
        ApprovalEntity approvalEntity = new ApprovalEntity();


        Page<ApprovalDto> approvalDtoPage = approvalService.apvList(pageable, subject, search, name);

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

    ///내가 쓴  보고서
    @GetMapping("/myApvList")
    public String myApvList(Model model,
                           @AuthenticationPrincipal MyUserDetails myUserDetails,
                           @RequestParam(name = "subject", required = false) String subject,
                           @RequestParam(name = "search", required = false) String search,
                           @PageableDefault(page = 0, size = 3, sort = "id", direction = Sort.Direction.DESC)
                           Pageable pageable){

        System.out.println(">>>>>>>>>"+myUserDetails.getName());
        model.addAttribute("myUserDetails",myUserDetails);
        Long memberId= myUserDetails.getMemberEntity().getId();
        Page<ApprovalDto> approvalDtoPage = approvalService.myApvList(pageable, subject, search, memberId);

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

        return "/apv/myApvList";
    }



    @GetMapping("/detail/{id}")
    public String detail(Model model, @PathVariable("id") Long id,
                         @AuthenticationPrincipal MyUserDetails myUserDetails){
        ApprovalDto approvalDto = approvalService.apvDetail(id);
        model.addAttribute("myUserDetails", myUserDetails);
        model.addAttribute("approvalDto",approvalDto);// entity -> dto 로 변환한 것
        return "apv/detail";
    }

    @GetMapping("/apvDelete/{id}")
    public String delete (@PathVariable("id") Long id){

        approvalService.apvDeleteById(id);
        return "redirect:/apv/list";
    }


    @PostMapping("/apvOK")
    public String apvOk(@ModelAttribute ApprovalDto approvalDto,Model model, @PathVariable("id") Long id ){
        ApprovalEntity approvalEntity = new ApprovalEntity();

        approvalService.apvOk(approvalDto);
        model.addAttribute("approvalDto",approvalDto);

        return "redirect:/apv/list";



    }


}
