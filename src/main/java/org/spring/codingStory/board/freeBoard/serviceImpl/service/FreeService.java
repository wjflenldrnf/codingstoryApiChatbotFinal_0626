package org.spring.codingStory.board.freeBoard.serviceImpl.service;


import org.spring.codingStory.board.freeBoard.dto.FreeDto;

import java.io.IOException;

public interface FreeService {

    void freeInsertFile(FreeDto freeDto) throws IOException;

    void updateFreeHit(Long id);
}
