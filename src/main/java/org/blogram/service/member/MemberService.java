package org.blogram.service.member;

import org.blogram.domain.member.Member;
import org.blogram.repository.member.MemberRepository;
import org.blogram.service.ValidCustomException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MemberService {
	private final MemberRepository memberRepository;
	private final PasswordEncoder passwordEncoder;

	@Autowired
	public MemberService(MemberRepository memberRepository, PasswordEncoder passwordEncoder) {
		this.memberRepository = memberRepository;
		this.passwordEncoder = passwordEncoder;
	}

	public List<MemberDto> getAllMembers() {
		return memberRepository.findAll().stream()
				.map(MemberDto::createFromEntity)
				.collect(Collectors.toList());
	}

    @Transactional
    public Long save(MemberRequestDto memberRequestDto) {
		verifyDuplicate(memberRequestDto);
        Member member = new Member();
        member.setName(memberRequestDto.getName());
        member.setEmail(memberRequestDto.getEmail());
        member.setPassword(passwordEncoder.encode(memberRequestDto.getPassword()));
        member.setBirthDate(memberRequestDto.getBirthDate());
    	return memberRepository.save(member).getId();
	}

	private void verifyDuplicate(MemberRequestDto memberRequestDto) {
	    verifyDuplicateName(memberRequestDto.getName());
	    verifyDuplicateEmail(memberRequestDto.getEmail());
    }

	private void verifyDuplicateEmail(String email) {
		if(memberRepository.findByEmail(email).isPresent()){
			throw new ValidCustomException("이미 사용중인 이메일주소입니다", "email");
		}
	}

	private void verifyDuplicateName(String name) {
	    if(memberRepository.findByName(name).isPresent()){
	        throw new ValidCustomException("이미 사용중인 아이디입니다", "name");
        }
    }
}
