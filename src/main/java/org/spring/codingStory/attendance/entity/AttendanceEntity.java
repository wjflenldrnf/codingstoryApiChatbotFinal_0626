package org.spring.codingStory.attendance.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.spring.codingStory.attendance.AttendanceStatus;
import org.spring.codingStory.attendance.dto.AttendanceDto;
import org.spring.codingStory.contraint.BaseTimeEntity;
import org.spring.codingStory.member.entity.MemberEntity;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

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

//    @Column(nullable = false)
//    @Temporal(TemporalType.TIMESTAMP)
//    private Date checkInTime;
//
//    @Column(nullable = false)
//    @Temporal(TemporalType.TIMESTAMP)
//    private Date checkOutTime;

    @Column
    private LocalDate attDate;

    @Column(nullable = false)
    private LocalTime attOnTime;

    @Column(nullable = false)
    private LocalTime attOffTime;

//    @Column(nullable = false)
//    private String attendanceType; // 정상 출근, 지각 등

    @Enumerated(EnumType.STRING)
    private AttendanceStatus attStatus;



    public void changeAttOnTime(LocalTime attOnTime) {
        this.attOnTime = attOnTime;
    }

    public void changeAttOffTime(LocalTime attOffTime) {this.attOffTime = attOffTime; }

    public void changeAttStatus(AttendanceStatus attStatus) {
        this.attStatus = attStatus;
    }



//    @Builder
//    public AttendanceEntity(LocalDate attDate, LocalTime attOnTime, LocalTime attOffTime, AttendanceStatus attStatus, MemberEntity memberEntity){
//        this.attDate = attDate;
//        this.attOnTime = attOnTime;
//        this.attOffTime = attOffTime;
//        this.attStatus = attStatus;
//        this.memberEntity = memberEntity;
//    }


    public AttendanceDto.Index entityToIndex(){
        return AttendanceDto.Index.builder()
                .attDate(getAttDate())
//                .empName(getEmployee().getEmpName())
                .memberName(getMemberEntity().getName())
                .memberDept(getMemberEntity().getDepartment())
                .attOnTime(getAttOnTime())
                .attOffTime(getAttOffTime())
                .attStatus(getAttStatus())
                .build();
    }



}
