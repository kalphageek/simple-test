package me.kalpha.memberframework.service;

import me.kalpha.memberframework.entity.MemberDto;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface MemberService extends UserDetailsService {
    Integer save(MemberDto memberDto);
}
