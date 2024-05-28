package org.spring.codingStory.chatbot.service;

import kr.co.shineware.nlp.komoran.core.Komoran;
import kr.co.shineware.nlp.komoran.model.KomoranResult;
import org.spring.codingStory.chatbot.dto.AnswerDTO;
import org.spring.codingStory.chatbot.dto.MessageDTO;
import org.spring.codingStory.chatbot.dto.PhoneInfo;
import org.spring.codingStory.chatbot.entity.AnswerEntity;
import org.spring.codingStory.chatbot.entity.ChatBotIntention;
import org.spring.codingStory.chatbot.repository.ChatBotIntentionRepository;
import org.spring.codingStory.member.entity.MemberEntity;
import org.spring.codingStory.member.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class KomoranService {

  @Autowired
  private Komoran komoran;

  @Autowired
  private MemberRepository memberRepository;

  public MessageDTO nlpAnalyze(String message) {

    KomoranResult result = komoran.analyze(message);

    // 김이름 전화 조회 해주세요.
    //문자에서 명사들만 추출한 목록 중복제거해서 set
    Set<String> nouns = result.getNouns().stream()
            .collect(Collectors.toSet());
    nouns.forEach((noun) -> {
      System.out.println(">>>:" + noun);
    });
    ;//메세지에서 명사추출

    return analyzeToken(nouns);
  }

  //입력된 목적어를 하나씩 파악하여 DB에서 검색하기위해 decisionTree()메서드로 전달
  private MessageDTO analyzeToken(Set<String> nouns) {

    LocalDateTime today = LocalDateTime.now();
    DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("a H:mm");
    MessageDTO messageDTO = MessageDTO.builder()
            .time(today.format(timeFormatter))//시간세팅
            .build();

    for (String token : nouns) {

      //1차의도가 존재하는지 파악
      Optional<ChatBotIntention> result = decisionTree(token, null);

      if (result.isEmpty()) continue;//존재하지 않으면 다음토큰 검색

      //1차 토근확인시 실행
      System.out.println(">>>>1차:" + token);
      //1차목록 복사
      Set<String> next = nouns.stream().collect(Collectors.toSet());
      //목록에서 1차토큰 제거
      next.remove(token);

      //2차분석 메서드
      AnswerDTO answer = analyzeToken(next, result).toAnswerDTO();

      //전화인경우 전화,전화번호 번호탐색
      if (token.contains("전화")) {
        PhoneInfo phone = analyzeTokenIsPhone(next);
        answer.phone(phone);//전화인경우에만 전화 데이터

      } else if (token.contains("안녕")) {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy년 MM월 dd일");
        messageDTO.today(today.format(dateFormatter));//처음 접속할때만 날짜표기
      } else if (token.contains("부서")) {
        PhoneInfo dept = analyzeTokenIsDept(next);
        System.out.println(dept.getDeptName()+" << 부서 이름");
        System.out.println(dept.getMemberName()+" <<  이름");
        answer.phone(dept);// 부서 -> 이름
      }

      messageDTO.answer(answer);//토근에대한 응답정보

      return messageDTO;
    }
    //분석 명사들이 등록한 의도와 일치하는게 존재하지 않을경우 null
    AnswerDTO answer = decisionTree("기타", null).get().getAnswer().toAnswerDTO();
    messageDTO.answer(answer);//토근에대한 응답정보
    return messageDTO;
  }

  private PhoneInfo analyzeTokenIsDept(Set<String> next) {

    for (String name : next) {

      System.out.println(name+" <<2차");
      Optional<MemberEntity> member = memberRepository.findByName(name);
      List<MemberEntity> memberEntityList = memberRepository.findAll();
      if (!member.isPresent()) continue;
      //존재하면
      String deptName = member.get().getDepartment();
      String phone = member.get().getPhoneNumber();
      String memberName = member.get().getName();
      String email=member.get().getUserEmail();

      System.out.println(member.get().getDepartment()+" << 부서 이름2");
      System.out.println(member.get().getName()+" <<  이름2");

      return PhoneInfo.builder()
              .deptName(deptName)
              .email(email)
              .memberName(memberName)
              .build();

    }
    return null;
  }

  //전화 문의인경우 DB에서 사원을 을 찾아서 처리
  private PhoneInfo analyzeTokenIsPhone(Set<String> next) {

    for (String name : next) {
      System.out.println(name+" <<2차2");

      Optional<MemberEntity> member = memberRepository.findByName(name);

      if (!member.isPresent()) continue;
      //존재하면
      String deptName = member.get().getDepartmentEntity().getDptName();
      String phone = member.get().getPhoneNumber();
      String memberName = member.get().getName();
      String email=member.get().getUserEmail();

      return PhoneInfo.builder()
              .email(email)
              .phone(phone)
              .memberName(memberName)
              .build();

    }
    return null;
  }

  //1차의도가 존재하면
  //하위의도가 존재하는지 파악
  private AnswerEntity analyzeToken(Set<String> next, Optional<ChatBotIntention> upper) {

    for (String token : next) {
      // 1차의도를 부모로하는 토큰이 존재하는지 파악
      Optional<ChatBotIntention> result = decisionTree(token, upper.get());
      if (result.isEmpty()) continue;

      System.out.println(">>>>2차:" + token);
      return result.get().getAnswer();//1차-2차 존재하는경우 답변
    }
    return upper.get().getAnswer();//1차만 존재하는 답변
  }

  @Autowired
  private ChatBotIntentionRepository intention;

  //의도가 존재하는지 DB에서 파악
  // 안녕 -> 등록
  private Optional<ChatBotIntention> decisionTree(String token, ChatBotIntention upper) {
    return intention.findByNameAndUpper(token, upper);
  }


}