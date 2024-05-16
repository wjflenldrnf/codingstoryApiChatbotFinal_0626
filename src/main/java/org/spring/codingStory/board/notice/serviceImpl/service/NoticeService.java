package org.spring.codingStory.board.notice.serviceImpl.service;


import org.spring.codingStory.board.notice.dto.NoticeDto;

import java.io.IOException;

public interface NoticeService {

    void noticeInsertFile(NoticeDto noticeDto) throws IOException;

    void updateNoticeHit(Long id);
}
