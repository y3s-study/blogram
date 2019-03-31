package org.blogram.service.post;

import com.sun.tools.doclets.internal.toolkit.util.MetaKeywords;
import org.blogram.domain.category.Category;
import org.blogram.domain.member.Member;
import org.blogram.domain.post.Post;
import org.blogram.repository.category.CategoryRepository;
import org.blogram.repository.member.MemberRepository;
import org.blogram.repository.post.PostRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PostServiceTest {
    @Autowired
    PostService postService;

    @Autowired
    PostRepository postRepository;

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void postSaveTest() throws Exception {
        // given
        PostSaveRequestDto postSaveRequestDto = PostSaveRequestDto.builder().title("test").content("test").category("java").member("user").build();

        // when
        postService.save(postSaveRequestDto);

        // then
        List<Post> result = postRepository.findByTitle(postSaveRequestDto.getTitle());
        assertEquals(result.get(0).getTitle(), postSaveRequestDto.getTitle());
    }
}