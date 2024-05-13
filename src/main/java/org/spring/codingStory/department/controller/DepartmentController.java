package org.spring.codingStory.department.controller;


import lombok.RequiredArgsConstructor;
import org.spring.codingStory.department.dto.DepartmentDto;
import org.spring.codingStory.department.serviceimpl.service.DepartmentService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;


@RequiredArgsConstructor
@Controller
@RequestMapping("/department")
public class DepartmentController {

  private final DepartmentService departmentService;

  @GetMapping("/departmentWrite")
  public String departmentWrite(){

    return "department/departmentWrite";
  }

  @PostMapping("departmentWrite")
  public String departmentWriteOK(DepartmentDto departmentDto)throws IOException{
    departmentService.insertdepartment(departmentDto);

    return "redirect:/department/departmentList";
  }




}
