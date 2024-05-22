package org.spring.codingStory.fullcalender.controller;

import org.spring.codingStory.config.MyUserDetails;
import org.spring.codingStory.fullcalender.serviceImpl.service.FullCalenderService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/my",produces = "application/json")
public class MainController {

  private final FullCalenderService fullCalenderService;

  public MainController(FullCalenderService fullCalenderService){
    this.fullCalenderService=fullCalenderService;
  }




  @GetMapping("/schedule")
  public String schedule(@AuthenticationPrincipal MyUserDetails myUserDetails, Model model){

    model.addAttribute("memberId",myUserDetails.getMemberEntity().getId());

    return "fullcalendar/mycalendar";
  }

}
