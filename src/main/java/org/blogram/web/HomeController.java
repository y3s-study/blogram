package org.blogram.web;

import org.blogram.service.post.PostDto;
import org.blogram.service.post.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {
	private final PostService postService;

	@Autowired
	public HomeController(PostService postService) {
		this.postService = postService;
	}

	@GetMapping("/")
	public String home(Model model) {
		List<PostDto> recentPosts = postService.getRecentPosts(3);
		model.addAttribute("recentPosts", recentPosts);
		return "home/home";
	}

}
