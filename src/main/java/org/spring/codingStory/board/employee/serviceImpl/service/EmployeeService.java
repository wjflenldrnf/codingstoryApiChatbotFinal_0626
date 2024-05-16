package org.spring.codingStory.board.employee.serviceImpl.service;

import org.spring.codingStory.board.employee.dto.EmployeeDto;

import java.io.IOException;

public interface EmployeeService {

    void empInsertFile(EmployeeDto employeeDto) throws IOException;
    void updateEmpHit(Long id);
}
