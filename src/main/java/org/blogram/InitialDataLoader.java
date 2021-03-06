package org.blogram;

import org.blogram.domain.category.Category;
import org.blogram.domain.member.Member;
import org.blogram.domain.post.Post;
import org.blogram.domain.role.Role;
import org.blogram.repository.category.CategoryRepository;
import org.blogram.repository.member.MemberRepository;
import org.blogram.repository.post.PostRepository;
import org.blogram.repository.role.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

/**
 * 로컬 개발에 필요한 테스트 데이터를 로드한다.
 */
@Profile("local")
@Component
public class InitialDataLoader implements ApplicationRunner {
	private final RoleRepository roleRepository;
	private final PostRepository postRepository;
	private final CategoryRepository categoryRepository;
	private final MemberRepository memberRepository;

	@Autowired
	public InitialDataLoader(RoleRepository roleRepository, PostRepository postRepository,
	                         CategoryRepository categoryRepository, MemberRepository memberRepository) {
		this.roleRepository = roleRepository;
		this.postRepository = postRepository;
		this.categoryRepository = categoryRepository;
		this.memberRepository = memberRepository;
	}

	@Override
	public void run(ApplicationArguments args) {
		initializeRepository();
	}

	private void initializeRepository() {
		Role adminRole = roleRepository.save(Role.create("ADMIN"));
		Role userRole = roleRepository.save(Role.create("USER"));

        Member admin = Member.builder()
		        .name("admin")
		        .email("admin@blogram.org")
		        .password("{bcrypt}$2a$10$9O/fZIW6GwcTTpyZjNRtLucJrLTGCLMDOJ0ioCZVYhQ4NMFPmO5jm")
		        .birthDate(LocalDate.now())
		        .build();
		admin.addRole(adminRole);
		admin.addRole(userRole);

		Member user = Member.builder()
				.name("user")
				.email("user@blogram.org")
				.password("{bcrypt}$2a$10$UupIyjwWPlZQzOZBZQo3lerUiDctVIUb.C6/a9iLDt2b51dyHiLUO")
				.birthDate(LocalDate.now())
				.build();
		user.addRole(userRole);

		memberRepository.save(admin);
		memberRepository.save(user);

		Category programmingCategory = Category.create("programming", user, null);
		categoryRepository.save(programmingCategory);

		Category javaCategory = Category.create("java", user, programmingCategory);
		categoryRepository.save(javaCategory);

		postRepository.save(Post.create("First post", "blah blah ~", user, javaCategory));
		postRepository.save(Post.create("Second Post", "adsf asdf zxcv qwer", user, javaCategory));
		postRepository.save(Post.create("Third Post", "adsf asdf zxcv qwer", admin, javaCategory));
		postRepository.save(Post.create("444444", "adsf asdf zxcv qwer 4번쨰", user, javaCategory));
	}
}
