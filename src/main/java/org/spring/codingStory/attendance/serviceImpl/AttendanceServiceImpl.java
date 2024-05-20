package org.spring.codingStory.attendance.serviceImpl;

import lombok.RequiredArgsConstructor;
import org.spring.codingStory.attendance.repository.AttendanceRepository;
import org.spring.codingStory.attendance.serviceImpl.service.AttendanceService;
import org.spring.codingStory.attendance.dto.AttendanceDto;
import org.spring.codingStory.attendance.entity.AttendanceEntity;
import org.spring.codingStory.member.entity.MemberEntity;
import org.spring.codingStory.member.repository.MemberRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class AttendanceServiceImpl implements AttendanceService {
    private final AttendanceRepository attendanceRepository;
    private final MemberRepository memberRepository;


    @Override
    public void insertCheckInAttendance(AttendanceDto attendanceDto) {

        attendanceDto.setMemberEntity(MemberEntity.builder()
                .id(attendanceDto.getMemberId())
                .build());

        AttendanceEntity attendanceEntity = AttendanceEntity.toInsertCheckInAttendanceEntity(attendanceDto);
        attendanceRepository.save(attendanceEntity);
    }

    @Override
    public List<AttendanceDto> attList() {

        List<AttendanceDto> attendanceDtoList = new ArrayList<>();
        List<AttendanceEntity> attendanceEntityList = attendanceRepository.findAll();


        return attendanceEntityList.stream().map(AttendanceDto::toSelectAllAttendanceDto)
                .collect(Collectors.toList());
    }

    @Override
    public Integer insertCheckInAttendance2(AttendanceDto attendanceDto) {

        attendanceDto.setMemberEntity(MemberEntity.builder()
                .id(attendanceDto.getMemberId())
                .build());

        AttendanceEntity attendanceEntity = AttendanceEntity.toInsertCheckInAttendanceEntity(attendanceDto);
        attendanceRepository.save(attendanceEntity);
        return 1;

    }

    @Override
    public int attendanceDelete(Long id) {

        Optional<AttendanceEntity> optionalAttendanceEntity = attendanceRepository.findById(id);

        if (optionalAttendanceEntity.isPresent()) {
            attendanceRepository.delete(optionalAttendanceEntity.get());
            return 1;
        }
        return 0;
    }


    @Override
    public int attendanceUpdate(Long id, AttendanceDto attendanceDto) {
        Optional<AttendanceEntity> optionalAttendanceEntity = attendanceRepository.findById(id);
        if (optionalAttendanceEntity.isPresent()) {
            AttendanceEntity attendanceEntity = optionalAttendanceEntity.get();

            LocalDateTime checkOutTime = LocalDateTime.now();

//            attendanceEntity.setCheckInTime(attendanceDto.getCheckInTime());
            attendanceEntity.setCheckOutTime(checkOutTime);
            attendanceEntity.setAttendanceType("퇴근");
            attendanceRepository.save(attendanceEntity);
            return 1;
        }
        return 0;
    }
}
