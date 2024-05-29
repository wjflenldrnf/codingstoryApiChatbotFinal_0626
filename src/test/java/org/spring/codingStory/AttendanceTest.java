package org.spring.codingStory;

import org.junit.jupiter.api.Test;
import org.spring.codingStory.attendance.dto.AttendanceDto;
import org.spring.codingStory.attendance.entity.AttendanceEntity;
import org.spring.codingStory.attendance.repository.AttendanceRepository;
import org.spring.codingStory.member.dto.MemberDto;
import org.spring.codingStory.member.entity.MemberEntity;
import org.spring.codingStory.member.repository.MemberFileRepository;
import org.spring.codingStory.member.repository.MemberRepository;
import org.spring.codingStory.member.role.Role;
import org.spring.codingStory.member.serviceImpl.service.MemberService;
import org.spring.codingStory.pay.dto.PayDto;
import org.spring.codingStory.pay.entity.PayEntity;
import org.spring.codingStory.pay.repository.PayRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.transaction.Transactional;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@SpringBootTest
@AutoConfigureMockMvc
public class AttendanceTest {

    @Autowired
    private MemberService memberService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private MemberFileRepository memberFileRepository;


    @Autowired
    MemberRepository memberRepository;

    @Autowired
    AttendanceRepository attendanceRepository;

    @Autowired
    PayRepository payRepository;


    @Transactional
    @Test
    void test5() {
        Long memberId = 5L;

        // 단계 1: daily wages 가져오기
        List<BigDecimal> dailyWages = attendanceRepository.findByAttendanceDailyWageNative(memberId);

        // 각 회원당 하나의 distinct daily wage만 있다고 가정
        if (!dailyWages.isEmpty()) {
            BigDecimal dailyWage = dailyWages.get(0);

            // 단계 2: PayDto 생성 (생성 방법이 있다고 가정)
            PayDto payDto = new PayDto();
            payDto.setMemberEntity(memberRepository.findById(memberId).orElse(null)); // 회원 엔티티 설정
            payDto.setPayBns("Some bonus"); // 필요한 다른 필드 설정
            payDto.setPaymentDate(LocalDate.now()); // 결제 날짜 설정

            // 단계 3: 계산된 PayMon으로 PayDto를 PayEntity로 변환
            PayEntity payEntity = PayEntity.toInsertPayEntity2(payDto, dailyWage);

            // 단계 4: PayEntity 저장 (저장할 레포지토리가 있다고 가정)
            payRepository.save(payEntity);

            // 단계 5: 검증을 위해 PayEntity 출력
            System.out.println(payEntity);
        } else {
            System.out.println("해당 ID를 가진 회원의 daily wages를 찾을 수 없습니다: " + memberId);
        }
    }



    @Transactional
    @Test
    void test4() {
        Long id = 5L;

        List<BigDecimal> dailyWages = attendanceRepository.findByAttendanceDailyWageNative(id);
        List<AttendanceDto> attendanceDtos = new ArrayList<>();

        attendanceDtos = dailyWages.stream()
                .map(AttendanceDto::toSelectDaliyWageAttendanceDto)
                .collect(Collectors.toList());

        for (AttendanceDto attendanceDto : attendanceDtos) {
//            System.out.println(attendanceDto);
            System.out.println("daily wage: " + attendanceDto.getDailyWage());
        }
    }





    @Transactional
    @Test
    void test3() {
        Long id = 5L;

        List<Time> workTimes = attendanceRepository.findByAttendanceWorkTimeNative(id);
        List<AttendanceDto> attendanceDtos = new ArrayList<>();

        attendanceDtos = workTimes.stream()
                .map(AttendanceDto::toSelectWorkTimeAttendanceDto)
                .collect(Collectors.toList());

        for (AttendanceDto attendanceDto : attendanceDtos) {
            System.out.println(attendanceDto);
        }
    }



    @Transactional
    @Test
    void test2() {

        MemberEntity memberEntity = new MemberEntity();

        Long id = 1L;

        //2.builder 이용
        AttendanceEntity attendanceEntity = AttendanceEntity.builder()
                .memberEntity(MemberEntity.builder().id(id).build())
                .checkInTime(LocalDateTime.now())
                .attendanceType("퇴근")
                .build();
        attendanceRepository.save(attendanceEntity);

        System.out.println(attendanceEntity);

    }


    @Transactional
    @Test
    void test1() {
//        Long id = 5L;

        List<AttendanceEntity> attendanceEntities = attendanceRepository.findByAttendanceNative();
        List<AttendanceDto> attendanceDtos = new ArrayList<>();
        attendanceDtos = attendanceEntities.stream()
                .map(AttendanceDto::toSelectAllAttendanceDto)
                .collect(Collectors.toList());

        for (AttendanceDto attendanceDto : attendanceDtos) {
            System.out.println(attendanceDto);
        }
    }



    @Test
    void testInsert() {


        Long id = 1L;


        AttendanceEntity attendanceEntity = AttendanceEntity.builder()
                .memberEntity(MemberEntity.builder().id(id).build())
                .checkInTime(LocalDateTime.now())
                .attendanceType("출근")
                .build();
        attendanceRepository.save(attendanceEntity);
    }

    @Test
    void admin() throws IOException {

        MemberDto memberDto;
        String adminFile = "admin.jpg";

        MemberEntity memberEntity = memberRepository.save(
                MemberEntity.builder()
                        .userEmail("admin@naver.com")
                        .userPw(passwordEncoder.encode("1234"))
                        .name("관리자")
                        .department("노원점")
                        .mRank("사장")
                        .address("서울")
                        .phoneNumber("0101234")
                        .role(Role.ADMIN)
                        .memberAttachFile(0)
                        .build());

        MemberEntity memberEntity1 = memberRepository.save(
                MemberEntity.builder()
                        .userEmail("m1@11.11")
                        .userPw(passwordEncoder.encode("11"))
                        .name("김1")
                        .department("노원점")
                        .mRank("부장")
                        .address("서울")
                        .phoneNumber("11")
                        .role(Role.MEMBER)
                        .memberAttachFile(0)
                        .build());

    }


    @Test
    void member() throws IOException {

        MemberDto memberDto;
        String adminFile = "admin.jpg";


        MemberEntity memberEntity1 = memberRepository.save(
                MemberEntity.builder()
                        .userEmail("m1@11.11")
                        .userPw(passwordEncoder.encode("11"))
                        .name("김1")
                        .department("노원점")
                        .mRank("부장")
                        .address("서울")
                        .phoneNumber("11")
                        .role(Role.MEMBER)
                        .memberAttachFile(0)
                        .build());

    }
}
