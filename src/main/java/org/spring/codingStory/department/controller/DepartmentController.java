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

  //부서를 추가하는 컨트롤러
  @GetMapping("/add")
  public String showAddDepartmentForm(Model model){
    model.addAttribute("departmentDto",new DepartmentDto());
    List<DepartmentEntity> parentDepartments= departmentService.getParentDepartments();
    model.addAttribute("parentDepartments",parentDepartments);
    return "department/add_department_form";
  }

  @PostMapping("/add")
  public String addDepartment(@ModelAttribute("departmentDto") DepartmentDto departmentDto){
    DepartmentEntity departmentEntity=departmentService.addDepartment(departmentDto);
    return "redirect:/department/department";
  }

  //하위부서 추가 컨트롤러
  @PostMapping("/addSubDepartment")
  public String addSubDepartment(@RequestParam Long parentDeptId, @ModelAttribute("departmentDto")DepartmentDto departmentDto){
      departmentService.addSubDepartment(parentDeptId, departmentDto);
      return "redirect:/department/department";
  }


  @GetMapping("/list")
  @ResponseBody
  public ResponseEntity<List<DepartmentEntity>>departmentList(){
    List<DepartmentEntity> departments=departmentService.getAllDepartments();

    return ResponseEntity.ok().body(departments);

  }



  //멤버추가컨트롤러
  @GetMapping("/members")
  public String showDepartmentMembers(@RequestParam Long deptId, Model model){
    DepartmentDto department= departmentService.getDepartmentByIdWithMembers(deptId);
    model.addAttribute("memberDto",new MemberDto());
    model.addAttribute("department",department);

    return "department/members";
  }
  // 부서 선택 및 초기 데이터를 로드하는 컨트롤러
  @GetMapping
  public String showDepartmentForm(Model model){
    List<DepartmentEntity> departmentEntities =departmentService.getAllDepartments();
    model.addAttribute("departmentEntities",departmentEntities);
    //초기 페이지 로드시 첫번째 부서를 선택하여 멤버 목록 로드
    if(!departmentEntities.isEmpty()){
      model.addAttribute("initialDeptId",departmentEntities.get(0).getId());
    }
    return "departmentForm";
  }


  @PostMapping("/addMember")
  public String addDepartmentMember(@RequestParam Long deptId, @ModelAttribute("memberDto")MemberDto memberDto){
    departmentService.adddMemberToDepartment(deptId, memberDto);
    return "redirect:/department/members?deptId=" + deptId;
  }



  //부서 추가시 부모 부서를 선택 할 수 있는 컨틀로러
  @GetMapping("/parents")
  @ResponseBody
  public ResponseEntity<List<DepartmentEntity>> getParentDepartments(){
    List<DepartmentEntity> parentDepartments= departmentService.getParentDepartments();
    return ResponseEntity.ok().body(parentDepartments);
  }



  }












