package org.spring.codingStory.pay.controller;

import lombok.RequiredArgsConstructor;
import org.spring.codingStory.config.MyUserDetails;
import org.spring.codingStory.pay.dto.PayDto;
import org.spring.codingStory.pay.serviceImpl.PayServiceImpl;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.io.IOException;
@RequiredArgsConstructor
@RequestMapping("/pay")
@Controller
public class PayController {

    private final PayServiceImpl payServiceImpl;

    @GetMapping({"/",""})
    public String payIndex(@AuthenticationPrincipal MyUserDetails myUserDetails, Model model){


        model.addAttribute("name" ,myUserDetails.getMemberEntity().getName());
        model.addAttribute("memberId",myUserDetails.getMemberEntity().getId());
        return "pay/payIndex";
    }

    //////////////////////////////////////////////////////////

    @GetMapping("/payWrite")
    public String write(PayDto payDto
            , @AuthenticationPrincipal MyUserDetails myUserDetails
            , Model model) {
        model.addAttribute("memberId", myUserDetails.getMemberEntity().getId());
        return "pay/payWrite";
    }


    @PostMapping("/payWrite")
    public String writeOk(@Valid PayDto payDto,
                          BindingResult bindingResult) throws IOException {

        if (bindingResult.hasErrors()) {
            return "pay/payWrite";
        }

        payServiceImpl.insertPay(payDto);
        return "redirect:/pay";
    }


}