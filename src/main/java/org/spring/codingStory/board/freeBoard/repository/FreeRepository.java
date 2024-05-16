package org.spring.codingStory.board.freeBoard.repository;

import org.spring.codingStory.board.freeBoard.entity.FreeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface FreeRepository extends JpaRepository<FreeEntity,Long> {
    @Modifying
    @Query(value = " update FreeEntity b set b.freeHit=b.freeHit+1  where b.id= :id ")
    void updateFreeHit(@Param("id") Long id);
}
