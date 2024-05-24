package org.spring.codingStory.pay.serviceImpl;

import lombok.RequiredArgsConstructor;
import org.spring.codingStory.member.entity.MemberEntity;
import org.spring.codingStory.pay.dto.PayDto;
import org.spring.codingStory.pay.entity.PayEntity;
import org.spring.codingStory.pay.repository.PayRepository;
import org.spring.codingStory.pay.serviceImpl.service.PayService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Transactional
@Service
@RequiredArgsConstructor
public class PayServiceImpl implements PayService {


    private final PayRepository payRepository;

    @Override
    public void insertPay(PayDto payDto) {

        payDto.setMemberEntity(MemberEntity.builder()
                .id(payDto.getMemberId())
                .build());

        PayEntity payEntity = PayEntity.toInsertPayEntity(payDto);
        payRepository.save(payEntity);

    }

    @Override
    public Page<PayDto> paySearchPagingList(Pageable pageable, String subject, String search) {

        Page<PayEntity> payEntities = null;


        //************************
        if (search == null || subject == null) {    //************************

            payEntities = payRepository.findAll(pageable);

        } else {

            if (subject.equals("payMon")) {
                payEntities = payRepository.findByPayMonContains(pageable, search);
            } else {
                payEntities = payRepository.findAll(pageable);
            }

        }


        Page<PayDto> payDtos = payEntities.map(PayDto::toSeletAllPayDto);

        return payDtos;

    }
}
