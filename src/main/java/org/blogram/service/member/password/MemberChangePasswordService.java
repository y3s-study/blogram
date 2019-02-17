package org.blogram.service.member.password;

import com.google.common.base.Preconditions;
import org.blogram.domain.member.Member;
import org.blogram.repository.member.MemberRepository;
import org.blogram.service.ValidCustomException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class MemberChangePasswordService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public MemberChangePasswordService(MemberRepository memberRepository, PasswordEncoder passwordEncoder) {
        this.memberRepository = memberRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void changePassword(String userName, ChangePasswordRequest changePasswordRequest) {
        Preconditions.checkNotNull(userName, "userName can not be null.");
        Preconditions.checkNotNull(changePasswordRequest, "changePasswordRequest can not be null.");

        memberRepository.findByName(userName)
                .filter(m -> isPasswordMatches(m, changePasswordRequest.getCurrentPassword()))
                .orElseThrow(() -> new ValidCustomException("회원정보가 존재하지 않습니다.", userName))
                .setPassword(passwordEncoder.encode(changePasswordRequest.getNewPassword()));
    }

    private boolean isPasswordMatches(Member member, String currentPassword) {
        return passwordEncoder.matches(currentPassword, member.getPassword());
    }
}
