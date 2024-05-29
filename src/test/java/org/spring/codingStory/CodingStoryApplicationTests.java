package org.spring.codingStory;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.spring.codingStory.department.controller.DepartmentController;
import org.spring.codingStory.department.entity.DepartmentEntity;
import org.spring.codingStory.department.serviceimpl.service.DepartmentService;
import org.spring.codingStory.member.entity.MemberEntity;
import org.spring.codingStory.member.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class CodingStoryApplicationTests {

	@InjectMocks
	private DepartmentController departmentController;

	@Mock
	private DepartmentService departmentService;







	@Autowired
	MemberRepository memberRepository;
	@Test
	void contextLoads() {


		List<MemberEntity> list=	memberRepository.findByDepartment("노원점");


		System.out.println(list);

	}



}
