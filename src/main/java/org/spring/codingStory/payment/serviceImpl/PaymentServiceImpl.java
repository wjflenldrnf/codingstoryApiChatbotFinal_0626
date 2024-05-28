package org.spring.codingStory.payment.serviceImpl;

import lombok.RequiredArgsConstructor;
import org.spring.codingStory.payment.serviceImpl.service.PaymentService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Transactional
@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {


}
