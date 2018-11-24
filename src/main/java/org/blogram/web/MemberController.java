package org.blogram.web;

import org.blogram.service.member.MemberRequestDto;
import org.blogram.service.member.MemberResponseDto;
import org.blogram.service.member.MemberService;
import org.blogram.service.post.PostDto;
import org.blogram.service.post.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/member")
public class MemberController {
    private static final String VIEW = "/member";

    @Autowired
    private MemberService memberService;

    @Autowired
    private PostService postService;

    /**
     * 프로필 페이지 이동
     * 회원
     * > 정보
     * > 포스트
     **/
    @GetMapping("/profile")
    public String profile(Model model, Principal principal) {
        List<PostDto> memberPosts = postService.getMemberPosts(principal.getName());
        model.addAttribute("loginUser", principal);
        model.addAttribute("memberPosts", memberPosts);
        return VIEW + "/home";
    }

    @PostMapping("/register")
    @ResponseBody
    public Long saveMember(@RequestBody @Valid MemberRequestDto memberRequestDto) {
        return memberService.save(memberRequestDto);
    }

    @GetMapping("/members")
    public List<MemberResponseDto> findAll() {
        return memberService.findAll();
    }
}
