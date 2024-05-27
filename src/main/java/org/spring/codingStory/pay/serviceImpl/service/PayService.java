package org.spring.codingStory.pay.serviceImpl.service;

import org.spring.codingStory.pay.dto.PayDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PayService {
    void insertPay(PayDto payDto);
//
//    Page<PayDto> paySearchPagingList(Pageable pageable, String subject, String search);
}
