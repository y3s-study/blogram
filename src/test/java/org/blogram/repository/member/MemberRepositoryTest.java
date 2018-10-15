package org.blogram.repository.member;

import org.blogram.domain.member.Member;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
@ActiveProfiles("test")
public class MemberRepositoryTest {
	@Autowired
	private TestEntityManager testEntityManager;
	@Autowired
	private MemberRepository memberRepository;

	@Before
	public void setUp() {
		Member member = Member.builder()
				.name("testUser")
				.email("test@test.com")
				.password("testPassword")
				.birthDate(LocalDate.of(1990, 3, 13))
				.lastLoginTime(LocalDateTime.of(2018, 1, 1, 0, 0))
				.build();

		testEntityManager.persist(member);
	}

	@Test
	public void findAll() {
		List<Member> findMembers = memberRepository.findAll();

		Optional<Member> member = findMembers.stream().findFirst();

		assertThat(member).isPresent();
		assertThat(member).hasValueSatisfying(m -> {
			assertThat(m.getName()).isEqualTo("testUser");
			assertThat(m.getEmail()).isEqualTo("test@test.com");
			assertThat(m.getPassword()).isEqualTo("testPassword");
			assertThat(m.getCreatedDate()).isNotNull();
			assertThat(m.getModifiedDate()).isNotNull();
		});
	}
}