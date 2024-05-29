package org.spring.codingStory.payment.serviceImpl.service;

import org.spring.codingStory.attendance.repository.AttendanceRepository;
import org.spring.codingStory.payment.dto.PaymentDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


public interface PaymentService {


    List<PaymentDto> paymentList();
}
