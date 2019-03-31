package org.blogram.web;

import org.blogram.service.member.password.ChangePasswordRequest;
import org.blogram.service.member.password.MemberChangePasswordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

@Controller
@RequestMapping("/member")
public class MemberChangePasswordController {
    private final MemberChangePasswordService memberChangePasswordService;

    @Autowired
    public MemberChangePasswordController(MemberChangePasswordService memberChangePasswordService) {
        this.memberChangePasswordService = memberChangePasswordService;
    }

    @GetMapping("/change-password")
    public String changePasswordView(Model model, Principal principal) {
        model.addAttribute("loginUser", principal);
        return "/member/changePassword";
    }

    @PostMapping("/change-password")
    @ResponseBody
    public ResponseEntity<String> changePassword(@RequestBody @Valid ChangePasswordRequest changePasswordRequest, Principal principal) {
        memberChangePasswordService.changePassword(principal.getName(), changePasswordRequest);
        return ResponseEntity.ok("success");
    }
}
