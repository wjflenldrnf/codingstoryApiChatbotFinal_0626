package org.spring.codingStory.member.serviceImpl;

import lombok.RequiredArgsConstructor;
import org.spring.codingStory.member.dto.MemberDto;
import org.spring.codingStory.member.entity.MemberEntity;
import org.spring.codingStory.member.repository.MemberRepository;
import org.spring.codingStory.member.serviceImpl.service.MemberService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class MemberServiceImpl implements MemberService {

  private final MemberRepository memberRepository;
  private final PasswordEncoder passwordEncoder;

  @Override
  public void memberJoin(MemberDto memberDto) {

    MemberEntity memberEntity=MemberEntity.toJoinMember(memberDto,passwordEncoder);
    memberRepository.save(memberEntity);

    }


}

