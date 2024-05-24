package org.spring.codingStory.fullcalender.serviceImpl.service;

import lombok.RequiredArgsConstructor;
import org.spring.codingStory.fullcalender.dto.FullCalenderDto;
import org.spring.codingStory.fullcalender.entity.FullCalenderEntity;
import org.spring.codingStory.fullcalender.reposiory.FullCalenderRepository;
import org.spring.codingStory.fullcalender.serviceImpl.FullCalenderServiceInterface;
import org.spring.codingStory.member.entity.MemberEntity;
import org.spring.codingStory.member.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Transactional
@Service
@RequiredArgsConstructor
public class FullCalenderService implements FullCalenderServiceInterface {

  private final FullCalenderRepository fullCalenderRepository;
  private static final Logger logger = Logger.getLogger(FullCalenderService.class.getName());
  private final MemberRepository memberRepository;


  public List<FullCalenderDto> fullCalenderListAll() {

    List<FullCalenderDto> fullCalenderDtoList=new ArrayList<>();
    List<FullCalenderEntity> fullCalenderEntities=fullCalenderRepository.findAll();

    for(FullCalenderEntity entity :fullCalenderEntities){
      FullCalenderDto fullCalenderDto=FullCalenderDto.builder()
          .id(entity.getId())
          .start(entity.getStart())
          .content(entity.getContent())
          .end(entity.getEnd())
          .build();

      fullCalenderDtoList.add(fullCalenderDto);
    }
    return fullCalenderDtoList;
  }


  public void setCalendar(FullCalenderDto dto) {
//    try {
      //사용자 정보를 가져오기
      Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
      String currentUsername= authentication.getName();

      System.out.println(dto.getStart()+" start ...");
      //현재 사용자의 정보를 MemberEntity로 가져옴
      Optional<MemberEntity> memberEntity=memberRepository.findByUserEmail(currentUsername);

      FullCalenderEntity entity = FullCalenderEntity
          .builder()
          .content(dto.getContent())
          .memberEntity(memberEntity.get())
          .start(dto.getStart())
          .end(dto.getEnd())
          .build();
      FullCalenderEntity fullCalenderEntity = fullCalenderRepository.save(entity);
      //저장이 성공 했을 때 추가적인 작업 수행을 가능하게 하기 위해서 추가
//    } catch (Exception e) {
//      //DB 저장 작업이 실패한 경우 처리
//      //예를 들어. 로깅하거나 사용자에게 메세지를 표시할 수 있음
//      logger.log(Level.SEVERE, "DB저장 작업 중 오류 발생: " + e.getMessage());
//      //또는 에외를 다시 던져서 상위 호출자에게 예외를 전파 할 수도 있음
//      throw new RuntimeException("DB저장 작업 중 오류 발생", e);
//    }
  }

  public List<FullCalenderDto> myFullCallerListAll(Long id) {
    MemberEntity memberEntity=memberRepository.findById(id).orElseThrow(IllegalArgumentException::new);

    List<FullCalenderEntity> fullCalenderDtoList= fullCalenderRepository.findByMemberEntity(memberEntity);


    return fullCalenderDtoList.stream().map(full->FullCalenderDto.builder()
        .start(full.getStart()).id(full.getId())
        .end(full.getEnd())
        .content(full.getContent())
        .build())
        .collect(Collectors.toList());


  }


  public void deleteCalendarEvent(Long eventId, String username) {

    fullCalenderRepository.deleteById(eventId);
  }
}
