package me.kalpha.memberframework.service;

import me.kalpha.memberframework.entity.Member;
import me.kalpha.memberframework.entity.MemberDto;
import me.kalpha.memberframework.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MemberServiceImpl implements MemberService{
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    PasswordEncoder passwordEncoder;

    @Transactional
    @Override
    public Integer save(MemberDto memberDto) {
        Member member = memberDto.toEntity();
        member.setLastAccessDt(LocalDateTime.now());
        member.setRegDt(LocalDateTime.now());

        member.setPassword(passwordEncoder.encode(member.getPassword()));
        return memberRepository.save(member).getId();
    }

    @Override
    public UserDetails loadUserByUsername(String account) throws UsernameNotFoundException {
        Optional<Member> optionalMember = memberRepository.findByAccount(account);
        Member member = optionalMember.orElseThrow(() -> new UsernameNotFoundException(account));

        return new User(member.getAccount(), member.getPassword(), authrities(member.getRole()));
    }

    private Collection<? extends GrantedAuthority> authrities(Member.Role role) {
        return Arrays.asList(new SimpleGrantedAuthority(role.name()));
    }

}
