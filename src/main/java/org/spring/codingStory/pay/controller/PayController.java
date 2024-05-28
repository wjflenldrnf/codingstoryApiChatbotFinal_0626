package org.spring.codingStory.pay.controller;

import lombok.RequiredArgsConstructor;
import org.spring.codingStory.config.MyUserDetails;
import org.spring.codingStory.member.entity.MemberEntity;
import org.spring.codingStory.pay.dto.PayDto;
import org.spring.codingStory.pay.serviceImpl.PayServiceImpl;
import org.spring.codingStory.payment.dto.PaymentDto;
import org.spring.codingStory.payment.serviceImpl.PaymentServiceImpl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/pay")
@Controller
public class PayController {

    private final PayServiceImpl payServiceImpl;
    private final PaymentServiceImpl paymentServiceImpl;



    //////////////////////////////////////////////////////////

    @GetMapping({"/", ""})
    public String payIndex(@AuthenticationPrincipal MyUserDetails myUserDetails,
                           @RequestParam(name = "subject", required = false) String subject,
                           @RequestParam(name = "search", required = false) String search,
                           Model model) {

        List<PaymentDto> paymentList = new ArrayList<>();
        if (subject == null || search == null) {

            paymentList = paymentServiceImpl.paymentList();
        } else {
//            paymentDtoList=paymentServiceImpl.paymentDtoSearchList(subject,search);
            paymentList = paymentServiceImpl.paymentList();
        }

        model.addAttribute("paymentList", paymentList);

        model.addAttribute("name", myUserDetails.getMemberEntity().getName());
        model.addAttribute("memberId", myUserDetails.getMemberEntity().getId());
        return "pay/payIndex";
    }

    //////////////////////////////////////////////////////////

    @PostMapping("/payUpdate")
    public String payUpdate(@ModelAttribute PayDto payDto,
                            BindingResult bindingResult) throws IOException {
        if (bindingResult.hasErrors()) {
            return "pay/payDetail"; // 오류가 있을 경우 적절한 뷰로 이동
        }

        if (payDto.getId() == null) {
            throw new IllegalArgumentException("The given id must not be null!");
        }

        payServiceImpl.updateOk(payDto);

        return "redirect:/pay/payDetail/" + payDto.getMemberId();
    }

    //////////////////////////////////////////////////////////


    @GetMapping("/payWrite/{memberId}")
    public String write(@PathVariable("memberId") Long memberId, Model model) {
        PayDto payDto = new PayDto();
        payDto.setMemberEntity(new MemberEntity());
        payDto.getMemberEntity().setId(memberId);

        model.addAttribute("payDto", payDto);
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

    //////////////////////////////////////////////////////////////

    @GetMapping("/payDetail/{memberId}")
    public String payByMemberId(
            @PathVariable("memberId") Long memberId,
            Model model) {

        List<PayDto> payList = payServiceImpl.findByMemberId(memberId);
        if (payList.isEmpty()) {
            return "error/404";
        }

//        model.addAttribute("pay", payList.get(0)); // 첫 번째 Pay 엔티티만 표시
        model.addAttribute("pay", payList.get(payList.size() - 1));
        return "pay/payDetail";
    }

    //////////////////////////////////////////////////////////





//    //////////////////////////////////////////////////////////////
//
//    @GetMapping("/payList")   //paging, reply
//    public String payList(
//            @RequestParam(name = "subject", required = false) String subject
//            , @RequestParam(name = "search", required = false) String search
//            , @PageableDefault(page = 0, size = 5, sort = "id"
//            , direction = Sort.Direction.DESC) Pageable pageable
//            , Model model) {
//
//        Page<PayDto> pagingList = payServiceImpl.paySearchPagingList(pageable, subject, search);
//
//
//        int totalPages = pagingList.getTotalPages(); //전체 페이지
//        int nowPage = pagingList.getNumber(); //현재 페이지
//        long totalElements = pagingList.getTotalElements(); //전체 레코드 개수
//        int size = pagingList.getSize(); //페이지 당 보이는 개수
//
//        int blockNum = 3; // 브라우저에 보이는 페이지 번호
//
//        int startPage = (int) ((Math.floor(nowPage / blockNum) * blockNum) + 1 <= totalPages ? (Math.floor(nowPage / blockNum) * blockNum) + 1 : totalPages);
//        int endPage = (startPage + blockNum) - 1 < totalPages ? (startPage + blockNum) - 1 : totalPages;
//
//
//        model.addAttribute("startPage", startPage);
//        model.addAttribute("endPage", endPage);
//        model.addAttribute("pagingList", pagingList);
//
//        return "pay/payList";
//    }


}
