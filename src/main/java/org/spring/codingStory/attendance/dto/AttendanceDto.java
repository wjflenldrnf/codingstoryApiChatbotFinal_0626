package org.spring.codingStory.attendance.dto;

import lombok.*;
import org.spring.codingStory.attendance.AttendanceStatus;
import org.spring.codingStory.member.entity.MemberEntity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

//@AllArgsConstructor
//@NoArgsConstructor
//@Builder
//@Getter
//@Setter
public class AttendanceDto {


    @Builder
    @Getter @Setter
    public static class Index {

        private Long id;

        private MemberEntity memberEntity;

        private LocalDate attDate;

        private LocalTime attOnTime;
        private LocalTime attOffTime;

        private AttendanceStatus attStatus;


        private String memberName;
        private String memberDept;


    }

    @Builder
    @Getter @Setter
    public static class Status {
        private int onCnt;
        private int offCnt;
        private int absenceCnt;
        private int lateCnt;
        private int vacationCnt;
        private int sickCnt;
    }

    @Getter @Setter
    public static class StatusAndIndexWithPage {
        private AttendanceDto.Status status;
        private List<Index> attendanceList;
//        private PageResultDTO pageResultDTO;
    }

}

//public static AttendanceDto toInsertAttendanceDto(AttendanceEntity attendanceEntity){
//
//    return
//}

