package org.spring.codingStory.attendance.attendanceServiceImpl;

import lombok.RequiredArgsConstructor;
import org.spring.codingStory.attendance.AttendanceRepository;
import org.spring.codingStory.attendance.AttendanceStatus;
import org.spring.codingStory.attendance.AttendanceTime;
import org.spring.codingStory.attendance.attendanceServiceImpl.attendanceService.AttendanceService;
import org.spring.codingStory.attendance.dto.AttendanceDto;
import org.spring.codingStory.attendance.entity.AttendanceEntity;
import org.spring.codingStory.member.entity.MemberEntity;
import org.spring.codingStory.member.repository.MemberRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class AttendanceServiceImpl implements AttendanceService{

    private final AttendanceRepository attendanceRepository;

    private final MemberRepository memberRepository;


    /**
     * 모든 직원의 근태이력을 자동 생성
     * detail, 시스템 00:00 기준 생성
     * @return 오늘 사원들 근태 이력
     */
    @Override
    @Transactional
    public List<AttendanceEntity> autoCreateAttendance(){
        // 모든 사람 이력 생성, 현재 결근 체크(null)
        for(MemberEntity m : memberRepository.findAll()){
            attendanceRepository.save(
                    AttendanceEntity.builder()
                            .attDate(LocalDate.now())
                            .attStatus(AttendanceStatus.ABSENCE)
                            .memberEntity(m)
                            .build()
            );
        }

        return attendanceRepository.findAllByDate(LocalDate.now());
    }

    /**
     * 출근을 할 경우
//     * @param member
     * @return 출근 처리된 사원
     */
    @Override
    @Transactional
    public AttendanceEntity onWork(MemberEntity memberEntity){
        List<AttendanceEntity> attendanceEntityList = attendanceRepository.findAllByMemberAndDate(memberEntity, LocalDate.now());

        // 출근 시간 전이라면
        if(LocalTime.now().isBefore(AttendanceTime.ON_TIME.getLocalTime())) {
            attendanceEntityList.get(0).changeAttStatus(AttendanceStatus.ON);
            attendanceEntityList.get(0).changeAttOnTime(LocalTime.now());
            // 출근 시간 이후라면
        }else{
            attendanceEntityList.get(0).changeAttStatus(AttendanceStatus.LATE);
            attendanceEntityList.get(0).changeAttOnTime(LocalTime.now());
        }
        return attendanceEntityList.get(0);
    }

    /**
     * 퇴근을 할 경우
//     * @param member
     * @return 퇴근 처리된 사원
     */
    @Override
    @Transactional
    public AttendanceEntity offWork(MemberEntity memberEntity){
        List<AttendanceEntity> attendanceEntityList = attendanceRepository.findAllByMemberAndDate(memberEntity, LocalDate.now());
        attendanceEntityList.get(0).changeAttStatus(AttendanceStatus.OFF);
        attendanceEntityList.get(0).changeAttOffTime(LocalTime.now());
        return attendanceEntityList.get(0);
    }


    @Override
    public List<AttendanceDto.Index> findAllById(Long id) {
        return attendanceRepository.findAllById(id).stream()
                .map(AttendanceEntity::entityToIndex)
                .collect(Collectors.toList());
    }
}
