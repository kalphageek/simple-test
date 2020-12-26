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

    /**
     * signup 화면 요청
      * @return
     */
    @GetMapping("/signup")
    public String signupView() {
        return "pages/signup";
    }

    /**
     * signup 화면에서 account 등록 요청
     * @param memberDto Guest가 사용자 정보를 입력한 데이터 객체
     * @return
     */
    @PostMapping("/signup")
    public String signup(MemberDto memberDto) {
        memberService.save(memberDto);
        return "redirect:/login";
    }

    /**
     * {@link me.kalpha.memberframework.config.SecurityConfig}에 "@EnableGlobalMethodSecurity(prePostEnabled = true)"가 있어야 @PreAuthorize가 동작한다
     * @return
     */
    @PreAuthorize("hasRole('ROLE_MEMBER')")
    @GetMapping("/member/info")
    public String userInfoView() {
        return "pages/user_info";
    }

    /**
     * {@link me.kalpha.memberframework.config.SecurityConfig}에 "@EnableGlobalMethodSecurity(prePostEnabled = true)"가 있어야 @PreAuthorize가 동작한다
     * @return
     */
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
