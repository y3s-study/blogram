package org.blogram;

import org.blogram.domain.member.Member;
import org.blogram.repository.member.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@Profile("local")
@Component
public class InitialDataLoader implements ApplicationRunner {
	private final MemberRepository memberRepository;

	@Autowired
	public InitialDataLoader(MemberRepository memberRepository) {
		this.memberRepository = memberRepository;
	}

	@Override
	public void run(ApplicationArguments args) {
		initializeMemberRepository();
	}

	private void initializeMemberRepository() {
		List<Member> members = Arrays.asList(
				Member.builder().name("admin").email("admin@blogram.org").password("admin").birthDate(LocalDate.now()).build(),
				Member.builder().name("user").email("user@blogram.org").password("user").birthDate(LocalDate.now()).build()
		);

		memberRepository.saveAll(members);
	}
}
