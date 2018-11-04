package org.blogram.web;

import org.blogram.service.member.MemberDto;
import org.blogram.service.member.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/member")
public class ProfileController {
    static final String VIEW = "/member";

    @Autowired
    MemberService memberService;

    @GetMapping("/profile")
    public String profile() {
        return VIEW + "/profile";
    }

    @PostMapping("/memberInfo")
    public List<MemberDto> memberInfo() {
        return memberService.getAllMembers();
    }
}
