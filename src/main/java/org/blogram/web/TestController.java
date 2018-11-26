package org.blogram.web;

import org.blogram.service.member.MemberDto;
import org.blogram.service.member.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class TestController {
	private final MemberService memberService;

	@Autowired
	public TestController(MemberService memberService) {
		this.memberService = memberService;
	}

	@GetMapping("/test")
	public String getMembers(Model model) {
		List<MemberDto> members = memberService.getAllMembers();
		model.addAttribute("members", members);
		return "test/memberList";
	}
}
