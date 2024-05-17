package org.spring.codingStory.board.employee.repository;

import org.spring.codingStory.board.employee.entity.EmployeeFileEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmpFileRepository extends JpaRepository<EmployeeFileEntity ,Long > {
}
