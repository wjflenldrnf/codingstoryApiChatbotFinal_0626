package org.spring.codingStory.board.notice.controller;

import lombok.RequiredArgsConstructor;
import org.spring.codingStory.board.notice.dto.NoticeDto;
import org.spring.codingStory.board.notice.serviceImpl.NoticeServiceImpl;
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
public class NoticeController {

    private NoticeServiceImpl noticeService;

    @GetMapping("/noticeWrite")
    public String noticeWrite(@AuthenticationPrincipal MyUserDetails myUserDetails,
                              NoticeDto noticeDto, Model model) {

        model.addAttribute("memberId", myUserDetails.getMemberEntity().getId());
        model.addAttribute("noticeDto", noticeDto);
        model.addAttribute("memberName", myUserDetails.getMemberEntity().getName());

        return "board/noticeWrite";
    }


    @PostMapping("/noticeWrite")
    public String noticeWriteOK(NoticeDto noticeDto,
                               @AuthenticationPrincipal MyUserDetails myUserDetails,
                               Model model) throws IOException {

        noticeService.noticeInsertFile(noticeDto);

        return "redirect:/board/noticeList";
        //글 작성후에 employeeList 페이지로 이동
    }
    
    
}
