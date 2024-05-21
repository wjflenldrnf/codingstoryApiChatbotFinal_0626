package org.spring.codingStory.department.controller;


import lombok.RequiredArgsConstructor;
import org.spring.codingStory.department.dto.DepartmentDto;
import org.spring.codingStory.department.entity.DepartmentEntity;
import org.spring.codingStory.department.serviceimpl.service.DepartmentService;
import org.spring.codingStory.member.dto.MemberDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;


@RequiredArgsConstructor
@Controller
@RequestMapping("/department")
public class DepartmentController {

  private final DepartmentService departmentService;


  // 새로운 매핑
  @GetMapping("/department")
  public String departmentPage() {
    return "department/department";
  }


  @GetMapping("/add")
  public String showAddDepartmentForm(Model model){
    model.addAttribute("departmentDto",new DepartmentDto());
    return "department/add_department_form";
  }

  @PostMapping("/add")
  public String addDepartment(@ModelAttribute("departmentDto") DepartmentDto departmentDto){
    DepartmentEntity departmentEntity=departmentService.addDepartment(departmentDto);
    return "redirect:/department/department";
  }

  @GetMapping("/list")
  @ResponseBody
  public ResponseEntity<List<DepartmentEntity>>departmentList(){
    List<DepartmentEntity> departments=departmentService.getAllDepartments();

    return ResponseEntity.ok().body(departments);

  }



  /*@GetMapping("/department/members")
  public String showDepartmentMembers(@RequestParam Long deptId){
     return "department/members";
  }
*/

  @GetMapping("/members")
  public String showDepartmentMembers(@RequestParam Long deptId, Model model){
    DepartmentDto department= departmentService.getDepartmentByIdWithMembers(deptId);
    model.addAttribute("memberDto",new MemberDto());
    model.addAttribute("department",department);

    return "department/members";
  }

  @PostMapping("/addMember")
  public String addDepartmentMember(@RequestParam Long deptId, @ModelAttribute("memberDto")MemberDto memberDto){
    departmentService.adddMemberToDepartment(deptId, memberDto);
    return "redirect:/department/members?deptId=" + deptId;
  }





  }












