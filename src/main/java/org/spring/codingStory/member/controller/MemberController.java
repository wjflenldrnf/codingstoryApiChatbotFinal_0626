package org.spring.codingStory.member.controller;

import lombok.RequiredArgsConstructor;
import org.spring.codingStory.member.dto.MemberDto;
import org.spring.codingStory.member.repository.MemberRepository;
import org.spring.codingStory.member.serviceImpl.MemberServiceImpl;
import org.spring.codingStory.member.serviceImpl.service.MemberService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@Controller
@RequestMapping("/member")
public class MemberController {

  private final MemberRepository memberRepository;
  private final MemberService memberService;


  @GetMapping("/join")
  public String join(){

    return "member/join";
  }
  @PostMapping("/join")
  public String joinGo(MemberDto memberDto){

  memberService.memberJoin(memberDto);


    return "redirect:/member/login";
  }

  @GetMapping("/login")
  public String login(){

    return "member/login";
  }







}
