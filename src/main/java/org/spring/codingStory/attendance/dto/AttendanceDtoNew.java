//package org.spring.codingStory.attendance.dto;
//
//import lombok.*;
//import org.spring.codingStory.attendance.AttendanceStatus;
//
//import java.time.LocalDate;
//import java.time.LocalTime;
//import java.util.List;
//
//@AllArgsConstructor
//@NoArgsConstructor
//@Builder
//@Getter
//@Setter
//public class AttendanceDtoNew {
//
//
//    @Builder
//    @Getter @Setter
//    public static class Index {
//        private LocalDate attDate;
//        private LocalTime attOnTime;
//        private LocalTime attOffTime;
//        private AttendanceStatus attStatus;
//        private String empName;
//        private String empPosition;
//        private String deptName;
//        private String dayOfWeek;
//    }
//
//    @Builder
//    @Getter @Setter
//    public static class Status {
//        private int onCnt;
//        private int offCnt;
//        private int absenceCnt;
//        private int lateCnt;
//        private int vacationCnt;
//        private int sickCnt;
//    }
//
//    @Getter @Setter
//    public static class StatusAndIndexWithPage {
//        private AttendanceDtoNew.Status status;
//        private List<Index> attendanceList;
////        private PageResultDTO pageResultDTO;
//    }
//
//}
//
////public static AttendanceDto toInsertAttendanceDto(AttendanceEntity attendanceEntity){
////
////    return
////}
//
