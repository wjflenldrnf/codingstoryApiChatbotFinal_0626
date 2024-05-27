package org.spring.codingStory.attendance.serviceImpl;

import lombok.RequiredArgsConstructor;
import org.spring.codingStory.attendance.dto.AttendanceDto;
import org.spring.codingStory.attendance.entity.AttendanceEntity;
import org.spring.codingStory.attendance.repository.AttendanceRepository;
import org.spring.codingStory.attendance.serviceImpl.service.AttendanceService;
import org.spring.codingStory.member.entity.MemberEntity;
import org.spring.codingStory.member.repository.MemberRepository;
import org.spring.codingStory.pay.repository.PayRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
//import static org.spring.codingStory.pay.entity.PayEntity.toInsertPayEntity3;

@Service
@RequiredArgsConstructor
@Transactional
public class AttendanceServiceImpl implements AttendanceService {
    private final AttendanceRepository attendanceRepository;
    private final MemberRepository memberRepository;


    @Autowired
    private PayRepository payRepository;


    @Override
    public List<AttendanceDto> attList() {

        List<AttendanceDto> attendanceDtoList = new ArrayList<>();
        List<AttendanceEntity> attendanceEntityList = attendanceRepository.findAll();


        return attendanceEntityList.stream().map(AttendanceDto::toSelectAllAttendanceDto)
                .collect(Collectors.toList());
    }

    @Override
    public Integer insertCheckInAttendance(AttendanceDto attendanceDto) {

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
    @Transactional
    public int updateCheckOutAttendance(Long id, AttendanceDto attendanceDto) {

        Optional<AttendanceEntity> optionalAttendanceEntity = attendanceRepository.findById(id);
        if (optionalAttendanceEntity.isPresent()) {
            AttendanceEntity attendanceEntity = optionalAttendanceEntity.get();

            LocalDateTime checkOutTime = LocalDateTime.now();

            attendanceEntity.setCheckOutTime(checkOutTime);
            attendanceEntity.setAttendanceType("퇴근");
            attendanceEntity.setWorkTime(
                    attendanceEntity.calculationSetWorkTime(attendanceEntity.getCheckInTime(), checkOutTime)); // 총 근무 시간 입력



            // attendanceEntity 저장
            attendanceRepository.save(attendanceEntity);


            return 1;
        }
        return 0;
    }




    @Override
    public boolean hasAttendanceToday(Long memberId) {
        LocalDate today = LocalDate.now();
        LocalDateTime todayStart = today.atStartOfDay();
//        LocalDateTime todayEnd = today.atTime(23, 59, 59);
        LocalDateTime todayEnd = today.atStartOfDay();
        return attendanceRepository
                .existsByEmployeeIdAndStartTimeBetween(memberId, todayStart, todayEnd);
    }

}
