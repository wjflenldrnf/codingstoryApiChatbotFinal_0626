package org.spring.codingStory.attendance.repository;

import org.spring.codingStory.attendance.entity.AttendanceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.sql.Time;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface AttendanceRepository extends JpaRepository<AttendanceEntity, Long> {

    @Query("SELECT COUNT(a.id) > 0 FROM AttendanceEntity a WHERE a.memberEntity.id = :id AND a.checkInTime BETWEEN :start AND :end")
    boolean existsByEmployeeIdAndStartTimeBetween(@Param("id") Long id, @Param("start") LocalDateTime todayStart, @Param("end") LocalDateTime todayEnd);

    @Query(value = "select a.* from attendance_entity a inner join member m on a.member_id=m.member_id;"
            ,nativeQuery = true    )
    List<AttendanceEntity> findByAttendanceNative();

    @Query(value = "select distinct a.work_time from attendance_entity a where a.member_id = :id", nativeQuery = true)
    List<Time> findByAttendanceWorkTimeNative(@Param("id") Long id);

    @Query(value = "select distinct a.daily_wage from attendance_entity a where a.member_id = :id", nativeQuery = true)
    List<BigDecimal> findByAttendanceDailyWageNative(@Param("id") Long id);
}