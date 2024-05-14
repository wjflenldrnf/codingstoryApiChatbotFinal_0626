package org.spring.codingStory.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class HomeController {

    @GetMapping({"","/index"})
    public String index(){

        return "index";
    }

//    @GetMapping("/b")
//    public String bIndex(){
//        return "b";
//    }
//
//    @GetMapping("/admin")
//    public String admin(){
//        return "admin";
//    }

}
