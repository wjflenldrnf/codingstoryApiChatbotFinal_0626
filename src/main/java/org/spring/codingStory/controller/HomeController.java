package org.spring.codingStory.controller;
import org.spring.codingStory.config.MyUserDetails;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class HomeController {

    @GetMapping({"","/login"})
    public String login(){

        return "login";
    }

    @GetMapping("/index")
    public String index(@AuthenticationPrincipal MyUserDetails myUserDetails, Model model){

        model.addAttribute("myUserDetails",myUserDetails);

        return "index";
    }



}
