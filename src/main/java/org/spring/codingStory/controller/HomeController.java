package org.spring.codingStory.controller;

import org.spring.codingStory.config.MyUserDetails;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class HomeController {


    @GetMapping({"","/login"})
    public String login(@RequestParam(value="error", required = false) String error,
                        @RequestParam(value = "exception", required = false) String exception,
                        Model model){

        model.addAttribute("error",error);
        model.addAttribute("exception",exception);


        return "login";
    }

//    @GetMapping("/index")
//    public String index(@AuthenticationPrincipal MyUserDetails myUserDetails, Model model) {
//
//        model.addAttribute("myUserDetails", myUserDetails);
//
//        return "index";
//    }

    @GetMapping("/index")
    public String index(@AuthenticationPrincipal MyUserDetails myUserDetails, Model model) {

        model.addAttribute("myUserDetails", myUserDetails);

        model.addAttribute("name" ,myUserDetails.getMemberEntity().getName());
        model.addAttribute("memberId",myUserDetails.getMemberEntity().getId());

        return "index";
    }

    @GetMapping("/map")
    public String map() {
        return "map";
    }
    @GetMapping("/map1")
    public String map1() {
        return "map1";
    }
    @GetMapping("/map2")
    public String map2() {
        return "map2";
    }   @GetMapping("/map3")
    public String map3() {
        return "map3";
    }   @GetMapping("/map4")
    public String map4() {
        return "map4";
    }





}