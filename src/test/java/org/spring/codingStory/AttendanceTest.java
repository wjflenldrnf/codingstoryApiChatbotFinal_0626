package org.spring.codingStory;

import org.junit.jupiter.api.Test;
import org.spring.codingStory.attendance.repository.AttendanceRepository;
import org.spring.codingStory.attendance.entity.AttendanceEntity;
import org.spring.codingStory.member.entity.MemberEntity;
import org.spring.codingStory.member.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;


@SpringBootTest
public class AttendanceTest {

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    AttendanceRepository attendanceRepository;

    //1.게시글 작성
    @Test
    void testInsert() {


        Long id = 1L;

        //2.builder 이용
        AttendanceEntity attendanceEntity = AttendanceEntity.builder()
                .memberEntity(MemberEntity.builder().id(id).build())
                .checkInTime(LocalDateTime.now())
                .attendanceType("출근")
                .build();
        attendanceRepository.save(attendanceEntity);
    }


}
