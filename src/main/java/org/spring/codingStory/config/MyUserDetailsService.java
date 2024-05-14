package org.spring.codingStory.config;


import lombok.RequiredArgsConstructor;
import org.spring.codingStory.member.entity.MemberEntity;
import org.spring.codingStory.member.repository.MemberRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MyUserDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String userEmail) throws UsernameNotFoundException {

        MemberEntity memberEntity = memberRepository.findByUserEmail(userEmail).orElseThrow(() -> {
            throw new IllegalArgumentException("회원없음");
        });

        return new MyUserDetails(memberEntity);
    }

}
