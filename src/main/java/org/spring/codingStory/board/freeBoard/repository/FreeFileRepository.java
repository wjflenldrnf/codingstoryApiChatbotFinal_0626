package org.spring.codingStory.board.freeBoard.repository;

import org.spring.codingStory.board.freeBoard.entity.FreeFileEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FreeFileRepository extends JpaRepository<FreeFileEntity,Long> {

}
