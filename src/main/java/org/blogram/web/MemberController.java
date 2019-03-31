package org.blogram.web;

import org.blogram.service.post.PostDto;
import org.blogram.service.post.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/member")
public class MemberController {
    private static final String MEMBER_VIEW_ROOT = "/member";

    private final PostService postService;

    @Autowired
    public MemberController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/profile")
    public String profile(Model model, Principal principal) {
        List<PostDto> memberPosts = postService.getMemberPosts(principal.getName());
        model.addAttribute("loginUser", principal);
        model.addAttribute("memberPosts", memberPosts);
        return MEMBER_VIEW_ROOT + "/profile";
    }
}
