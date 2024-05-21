package org.spring.codingStory.board.freeBoard.controller;

import lombok.RequiredArgsConstructor;

import org.spring.codingStory.board.freeBoard.dto.FreeDto;
import org.spring.codingStory.board.freeBoard.serviceImpl.FreeServiceImpl;
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
public class FreeController {

    private FreeServiceImpl freeService;


    @GetMapping("/freeWrite")
    public String freeWrite(@AuthenticationPrincipal MyUserDetails myUserDetails,
                                 FreeDto freeDto, Model model) {

        model.addAttribute("memberId", myUserDetails.getMemberEntity().getId());
        model.addAttribute("freeDto", freeDto);
        model.addAttribute("memberName", myUserDetails.getMemberEntity().getName());

        return "board/freeWrite";
    }


    @PostMapping("/freeWrite")
    public String freeWriteOK(FreeDto freeDto,
                               @AuthenticationPrincipal MyUserDetails myUserDetails,
                               Model model) throws IOException {

        freeService.freeInsertFile(freeDto);

        return "redirect:/board/freeList";
        //글 작성후에 employeeList 페이지로 이동
    }
    
    
}
