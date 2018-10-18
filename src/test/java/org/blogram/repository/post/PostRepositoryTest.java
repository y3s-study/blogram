package org.blogram.repository.post;

import org.blogram.domain.member.Member;
import org.blogram.domain.post.Post;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class PostRepositoryTest {
	@Autowired
	private TestEntityManager testEntityManager;
	@Autowired
	private PostRepository postRepository;

	@Test
	public void testFindById() {
		// given
		Member testUser = Member.builder()
				.name("testUser")
				.email("test@test.com")
				.password("password")
				.birthDate(LocalDate.now())
				.build();

		testEntityManager.persist(testUser);
		Post post = Post.create("title", "content", testUser);
		Post persistedPost = testEntityManager.persist(post);

		// when
		Optional<Post> foundPostOptional = postRepository.findById(persistedPost.getId());

		// then
		assertThat(foundPostOptional).isPresent();
		assertThat(foundPostOptional).hasValueSatisfying(foundPost -> {
			assertThat(foundPost.getId()).isNotNull();
			assertThat(foundPost.getTitle()).isEqualTo("title");
			assertThat(foundPost.getContent()).isEqualTo("content");
			assertThat(foundPost.getMember()).isEqualTo(testUser);
			assertThat(foundPost.getCreatedDate()).isNotNull();
			assertThat(foundPost.getModifiedDate()).isNotNull();
		});
	}
}