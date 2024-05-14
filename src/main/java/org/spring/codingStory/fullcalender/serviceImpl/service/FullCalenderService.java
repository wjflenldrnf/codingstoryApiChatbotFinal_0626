package org.spring.codingStory.fullcalender.serviceImpl.service;

import lombok.RequiredArgsConstructor;
import org.spring.codingStory.fullcalender.entity.FullCalenderEntity;
import org.spring.codingStory.fullcalender.reposiory.FullCalenderRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FullCalenderService {

  private final FullCalenderRepository fullCalenderRepository;
}
