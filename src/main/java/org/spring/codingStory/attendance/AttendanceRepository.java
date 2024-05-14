package org.spring.codingStory.attendance;

import lombok.RequiredArgsConstructor;
import org.spring.codingStory.attendance.entity.AttendanceEntity;
import org.spring.codingStory.member.entity.MemberEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class AttendanceRepository {

    private final EntityManager em;

    public AttendanceEntity save(AttendanceEntity attendanceEntity) {

        em.persist(attendanceEntity);
        return attendanceEntity;
    }


    public List<AttendanceEntity> findAllByDate(LocalDate date) {
        return em.createQuery("select a from attendanceEntity a left join fetch a.memberEntity m left join fetch m.department where a.attDate = :att_date", AttendanceEntity.class)
                .setParameter("att_date", date)
                .getResultList();
    }

    public List<AttendanceEntity> findAllByMemberAndDate(MemberEntity memberEntity, LocalDate date) {

        return em.createQuery("select a from attendanceEntity a where a.memberEntity in :memberEntity and a.attDate = :att_date", AttendanceEntity.class)
                .setParameter("member", memberEntity)
                .setParameter("att_date", date)
                .getResultList();
    }


    public List<AttendanceEntity> findAllById(Long id){
        return em.createQuery("select a from attendanceEntity a join fetch a.memberEntity m join fetch m.department where m.Id = :member_id", AttendanceEntity.class)
                .setParameter("member_id", id)
                .getResultList();
    }

}
