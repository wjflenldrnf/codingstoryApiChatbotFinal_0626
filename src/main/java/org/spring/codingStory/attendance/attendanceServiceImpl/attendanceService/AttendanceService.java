package org.spring.codingStory.attendance.attendanceServiceImpl.attendanceService;

import org.spring.codingStory.attendance.dto.AttendanceDto;
import org.spring.codingStory.attendance.entity.AttendanceEntity;
import org.spring.codingStory.member.entity.MemberEntity;

import java.time.LocalDate;
import java.util.List;

public interface AttendanceService {

    public List<AttendanceEntity> autoCreateAttendance();


    public AttendanceEntity onWork(MemberEntity memberEntity);

    public AttendanceEntity offWork(MemberEntity memberEntity);

    List<AttendanceDto.Index> findAllById(Long id);

//    /**
//     * 해당 날짜 검색
//     * detail, 해당 날짜에 출근하지 않은 사람 상태(휴가, 결석 등) 표시해야함
//     * @param date
//     * @return 해당하는 날짜만 담은 근태 이력
//     */
//    public List<AttendanceDto.Index> findAllByDate(LocalDate date);
//
//
//    /**
//     * 전체 직원 출근 현황 보여주기
//     * detail, 출근, 결근, 지각, 휴가, 병가, 퇴근
//     * @return 오늘 날짜 모든 사원들의 출근 상태
//     */
//    public AttendanceDto.Status findAllOnlyStatus();


}
