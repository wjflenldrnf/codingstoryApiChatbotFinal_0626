package org.spring.codingStory.attendance.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.spring.codingStory.attendance.dto.AttendanceDto;
import org.spring.codingStory.contraint.BaseTimeEntity;
import org.spring.codingStory.member.entity.MemberEntity;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "attendanceEntity")
public class AttendanceEntity  extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "attendance_id")
    private Long id;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private MemberEntity memberEntity;

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date checkInTime;

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date checkOutTime;

    @Column
    private String attendanceType; // 정상 출근, 지각 등


    public static AttendanceEntity toInsertCheckInAttendanceEntity(AttendanceDto attendanceDto) {
        AttendanceEntity attendanceEntity=new AttendanceEntity();

        attendanceEntity.setMemberEntity(attendanceDto.getMemberEntity());
        attendanceEntity.setCheckInTime(attendanceDto.getCheckInTime());
//        attendanceEntity.setCheckInTime(LocalDateTime.now());
        attendanceEntity.setCheckOutTime(attendanceDto.getCheckOutTime());
        attendanceEntity.setAttendanceType(attendanceDto.getAttendanceType());

        return attendanceEntity;
    }
}