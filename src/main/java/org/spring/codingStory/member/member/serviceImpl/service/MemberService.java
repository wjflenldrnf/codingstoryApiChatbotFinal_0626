package org.spring.codingStory.member.member.serviceImpl.service;

import lombok.RequiredArgsConstructor;
import org.spring.codingStory.member.dto.MemberDto;
import org.spring.codingStory.member.entity.MemberEntity;
import org.spring.codingStory.member.repository.MemberRepository;
import org.spring.codingStory.member.serviceImpl.MemberServiceInterface;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MemberService implements MemberServiceInterface {

  private final MemberRepository memberRepository;


  @Override
  public void memberJoin(MemberDto memberDto) {



    MemberEntity memberEntity=MemberEntity.toJoinMember(memberDto);
    memberRepository.save(memberEntity);







    }

  }

