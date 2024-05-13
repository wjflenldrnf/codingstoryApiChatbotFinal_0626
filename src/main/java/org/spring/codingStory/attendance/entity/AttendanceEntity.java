package org.spring.codingStory.attendance.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.spring.codingStory.contraint.BaseTimeEntity;
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
public class AttendanceEntity  {
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
//    private LocalDateTime checkInTime;
//
//    @Column(nullable = false)
//    @Temporal(TemporalType.TIMESTAMP)
//    private LocalDateTime checkOutTime;

    @Column(nullable = false)
    private String attendanceType; // 정상 출근, 지각 등



}
