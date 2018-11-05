package org.blogram.web;

import org.blogram.domain.member.PostListRq;
import org.blogram.domain.member.PostListRs;
import org.blogram.service.member.MemberDto;
import org.blogram.service.member.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/member")
public class MemberController {
    static final String VIEW = "/member";

    @Autowired
    MemberService memberService;

    /**
     * post 페이지 이동
     **/
    @GetMapping("/post")
    public String post() {
        return VIEW + "/post";
    }

    /**
     * 회원 정보 가져오기
     **/
    @PostMapping("/memberInfo")
    public List<MemberDto> memberInfo() {
        return memberService.getAllMembers();
    }

    /**
     * 회원 포트트 정보 가져오기
     **/
    @PostMapping("/postList")
    @ResponseBody
    public PostListRs postList(@RequestBody PostListRq postListRq){
        return memberService.postList(postListRq);
    }
}
