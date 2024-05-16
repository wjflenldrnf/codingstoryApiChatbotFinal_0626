package org.spring.codingStory.attendance.attendanceServiceImpl;

import lombok.RequiredArgsConstructor;
import org.spring.codingStory.attendance.AttendanceRepository;
import org.spring.codingStory.attendance.attendanceServiceImpl.attendanceService.AttendanceService;
import org.spring.codingStory.attendance.dto.AttendanceDto;
import org.spring.codingStory.attendance.entity.AttendanceEntity;
import org.spring.codingStory.member.entity.MemberEntity;
import org.spring.codingStory.member.repository.MemberRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class AttendanceServiceImpl implements AttendanceService {
    private final AttendanceRepository attendanceRepository;
    private final MemberRepository memberRepository;


    @Override
    public void insertCheckInAttendance(AttendanceDto attendanceDto) {
//        MemberEntity memberEntity = MemberEntity.builder().id(attendanceDto.getMemberEntity().getId()).build();

        attendanceDto.setMemberEntity(MemberEntity.builder()
                .id(attendanceDto.getMemberId())
                .build());

        AttendanceEntity attendanceEntity= AttendanceEntity.toInsertCheckInAttendanceEntity(attendanceDto);
        attendanceRepository.save(attendanceEntity);
    }
}
