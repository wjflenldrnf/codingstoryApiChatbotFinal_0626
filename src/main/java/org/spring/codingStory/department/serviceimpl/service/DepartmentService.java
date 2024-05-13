package org.spring.codingStory.department.serviceimpl.service;

import lombok.RequiredArgsConstructor;
import org.spring.codingStory.department.dto.DepartmentDto;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;

@Transactional
@Service
@RequiredArgsConstructor
public class DepartmentService {
  public void insertdepartment(DepartmentDto departmentDto) throws IOException {

  }
}
