package org.spring.codingStory.attendance.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.spring.codingStory.attendance.dto.AttendanceDto;
import org.spring.codingStory.member.entity.MemberEntity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Time;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "attendanceEntity")
public class AttendanceEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "attendance_id")
    private Long id;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private MemberEntity memberEntity;

//    @Column
//    @Temporal(TemporalType.TIMESTAMP)
//    private Date checkInTime;
//
//    @Column
//    @Temporal(TemporalType.TIMESTAMP)
//    private Date checkOutTime;

    @Column
    private LocalDateTime checkInTime;

    @Column
    private LocalDateTime checkOutTime;

    @Column
    private String attendanceType; // 정상 출근, 지각 등



    @Column
    private Time workTime;

//    @Column
//    private BigDecimal dailyWage;
//
//    @Column
//    private LocalDate workDay;
//
//    @Column
//    private BigDecimal weeklyAllowance;
//
//    @Column
//    private BigDecimal bonus;


    public Time calculationSetWorkTime(LocalDateTime checkInTime, LocalDateTime checkOutTime) {
        Duration duration = Duration.between(checkInTime, checkOutTime);

        // Duration 객체를 통해 시간과 분 계산
        long hours = duration.toHours();
        long minutes = duration.toMinutes() % 60;

        LocalTime totalTime = LocalTime.of((int) hours, (int) minutes);

        // LocalTime 객체를 Time 타입으로 변환
        return Time.valueOf(totalTime);
    }


    public static AttendanceEntity toInsertCheckInAttendanceEntity(AttendanceDto attendanceDto) {

        LocalDateTime checkInTime = LocalDateTime.now();
        LocalDateTime checkOutTime = LocalDateTime.now();

        AttendanceEntity attendanceEntity=new AttendanceEntity();

        attendanceEntity.setMemberEntity(attendanceDto.getMemberEntity());
        attendanceEntity.setCheckInTime(checkInTime);
//        attendanceEntity.setCheckOutTime(checkOutTime);
//        attendanceEntity.setAttendanceType(attendanceDto.getAttendanceType());
        attendanceEntity.setAttendanceType("출근");

        return attendanceEntity;
    }

    ////////////////////////////////////////////////////////

    public static AttendanceEntity toUpdateCheckOutAttendanceEntity(AttendanceDto attendanceDto) {

        LocalDateTime checkInTime = LocalDateTime.now();
        LocalDateTime checkOutTime = LocalDateTime.now();

        AttendanceEntity attendanceEntity=new AttendanceEntity();

        attendanceEntity.setMemberEntity(attendanceDto.getMemberEntity());
//
        attendanceEntity.setId(attendanceDto.getId());

//        attendanceEntity.setCheckInTime(checkInTime);
        attendanceEntity.setCheckOutTime(checkOutTime);
        attendanceEntity.setAttendanceType(attendanceDto.getAttendanceType());


        attendanceEntity.setAttendanceType("퇴근");


        attendanceDto.setMemberEntity(MemberEntity.builder()
                .id(attendanceDto.getMemberId())
                .build());

        return attendanceEntity;

    }
}