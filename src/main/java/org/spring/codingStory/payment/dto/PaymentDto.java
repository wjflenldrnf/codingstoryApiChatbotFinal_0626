package org.spring.codingStory.payment.dto;

import lombok.RequiredArgsConstructor;
import org.spring.codingStory.member.entity.MemberEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Transactional
@Service
@RequiredArgsConstructor
public class PaymentDto {

    private Long id;

    private MemberEntity memberEntity;

    private String hourlyWage;


}
