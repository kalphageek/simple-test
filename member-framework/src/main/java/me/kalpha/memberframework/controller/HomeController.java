package me.kalpha.memberframework.controller;

import me.kalpha.memberframework.entity.MemberDto;
import me.kalpha.memberframework.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class HomeController {
    @Autowired
    MemberService memberService;

    @GetMapping
    public String homeView() {
        return "pages/home";
    }

    @GetMapping("/login")
    public String loginView() {
        return "pages/login";
    }

    @GetMapping("/signup")
    public String signupView() {
        return "pages/signup";
    }

    @PostMapping("/signup")
    public String signup(MemberDto memberDto) {
        memberService.save(memberDto);
        return "redirect:/login";
    }

    @PreAuthorize("hasRole('ROLE_MEMBER')")
    @GetMapping("/member/info")
    public String userInfoView() {
        return "pages/user_info";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/admin")
    public String adminView() {
        return "pages/admin";
    }

    @GetMapping("/denied")
    public String deniedView() {
        return "pages/denied";
    }
}
