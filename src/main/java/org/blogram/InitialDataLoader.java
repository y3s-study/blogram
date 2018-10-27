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
import java.util.Arrays;
import java.util.List;

/**
 * 로컬 개발에 필요한 테스트 데이터를 로드한다.
 */
@Profile("local")
@Component
public class InitialDataLoader implements ApplicationRunner {
	private final RoleRepository roleRepository;
	private final MemberRepository memberRepository;
	private final PostRepository postRepository;
	private final CategoryRepository categoryRepository;

	@Autowired
	public InitialDataLoader(RoleRepository roleRepository, MemberRepository memberRepository,
	                         PostRepository postRepository, CategoryRepository categoryRepository) {
		this.roleRepository = roleRepository;
		this.memberRepository = memberRepository;
		this.postRepository = postRepository;
		this.categoryRepository = categoryRepository;
	}

	@Override
	public void run(ApplicationArguments args) {
		initializeRepository();
	}

	private void initializeRepository() {
		Role adminRole = roleRepository.save(Role.create("ADMIN"));
		Role userRole = roleRepository.save(Role.create("USER"));

		Member admin = Member.builder().name("admin").email("admin@blogram.org").password("admin").birthDate(LocalDate.now()).build();
		admin.addRole(adminRole);
		admin.addRole(userRole);

		Member user = Member.builder().name("user").email("user@blogram.org").password("user").birthDate(LocalDate.now()).build();
		user.addRole(userRole);

		List<Member> members = Arrays.asList(admin, user);
		memberRepository.saveAll(members);

		Category programmingCategory = Category.create("programming", user, null);
		categoryRepository.save(programmingCategory);

		Category javaCategory = Category.create("java", user, programmingCategory);
		categoryRepository.save(javaCategory);

		Post testPost = Post.create("test post", "blah blah ~", user, javaCategory);
		postRepository.save(testPost);
	}
}
