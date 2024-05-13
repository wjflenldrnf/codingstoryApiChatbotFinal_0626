package org.spring.codingStory.attendance;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class attendanceController {

  @GetMapping({"","/index","/a"})
  public String index(){
    return "a";
  }
  @GetMapping("/b")
  public String bindex(){
    return "b";
  }
  @GetMapping("/admin")
  public String admin(){
    return "admin";
  }
}
