package org.blogram.web;


import org.blogram.service.post.PostSaveRequestDto;
import org.blogram.service.post.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;

@Controller
public class PostController {

    @Autowired
    PostService postService;

    @PostMapping("/postMakeUp")
    @ResponseBody
    public long makeUpPost(@RequestBody PostSaveRequestDto dto, Principal principal) {
        dto.setMember(principal.getName());
        return postService.save(dto);
    }

}
