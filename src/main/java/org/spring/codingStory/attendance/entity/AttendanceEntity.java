package org.spring.codingStory.attendance.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.spring.codingStory.attendance.dto.AttendanceDto;
import org.spring.codingStory.member.entity.MemberEntity;

import javax.persistence.*;
import java.time.LocalDateTime;

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


    public static AttendanceEntity toInsertCheckInAttendanceEntity(AttendanceDto attendanceDto) {

        LocalDateTime checkInTime = LocalDateTime.now();
        LocalDateTime checkOutTime = LocalDateTime.now();

        AttendanceEntity attendanceEntity=new AttendanceEntity();

        attendanceEntity.setMemberEntity(attendanceDto.getMemberEntity());
        attendanceEntity.setCheckInTime(checkInTime);
//        attendanceEntity.setCheckOutTime(checkOutTime);
        attendanceEntity.setAttendanceType(attendanceDto.getAttendanceType());

        return attendanceEntity;
    }
}