package org.blogram.service.member;

import org.blogram.domain.member.Member;
import org.blogram.repository.member.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MemberService implements UserDetailsService {

	private MemberRepository memberRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	public MemberService(MemberRepository memberRepository) {
		this.memberRepository = memberRepository;
	}

	public List<MemberDto> getAllMembers() {
		return memberRepository.findAll().stream()
				.map(MemberDto::createFromEntity)
				.collect(Collectors.toList());
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Member member = memberRepository.findByName(username);

		// db에서 받아와야 함
		List<GrantedAuthority> authorities = new ArrayList<>();

		return new User(member.getName(), member.getPassword(), authorities);
	}

	public Member save(Member member) {
		member.setPassword(passwordEncoder.encode(member.getPassword()));
		return memberRepository.save(member);
	}
}
