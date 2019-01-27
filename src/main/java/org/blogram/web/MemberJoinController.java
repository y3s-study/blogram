package org.blogram.web;

import org.blogram.service.member.MemberRequestDto;
import org.blogram.service.member.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;

@Controller
public class MemberJoinController {

    @Autowired
    private MemberService memberService;

    @PostMapping("/register")
    @ResponseBody
    public Long saveMember(@RequestBody @Valid MemberRequestDto memberRequestDto) {
        return memberService.save(memberRequestDto);
    }

    @GetMapping("/join")
    public String join() {
        return "/join";
    }
}
