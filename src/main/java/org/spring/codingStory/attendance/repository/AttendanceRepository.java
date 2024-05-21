package org.spring.codingStory.attendance.repository;

import org.spring.codingStory.attendance.entity.AttendanceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface AttendanceRepository extends JpaRepository<AttendanceEntity, Long> {

    @Query("SELECT COUNT(a.id) > 0 FROM AttendanceEntity a WHERE a.memberEntity.id = :id AND a.checkInTime BETWEEN :start AND :end")
    boolean existsByEmployeeIdAndStartTimeBetween(@Param("id") Long id, @Param("start") LocalDateTime todayStart, @Param("end") LocalDateTime todayEnd);
}