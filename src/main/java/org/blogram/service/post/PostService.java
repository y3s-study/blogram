package org.blogram.service.post;

import org.blogram.domain.category.Category;
import org.blogram.domain.member.Member;
import org.blogram.domain.post.Post;
import org.blogram.repository.category.CategoryRepository;
import org.blogram.repository.member.MemberRepository;
import org.blogram.repository.post.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

import static java.util.stream.Collectors.toList;
import static org.blogram.util.BlogramStreamUtils.asStream;

@Service
public class PostService {
    private final PostRepository postRepository;
    private final CategoryRepository categoryRepository;
    private final MemberRepository memberRepository;

    @Autowired
    public PostService(PostRepository postRepository, CategoryRepository categoryRepository, MemberRepository memberRepository) {
        this.postRepository = postRepository;
        this.categoryRepository = categoryRepository;
        this.memberRepository = memberRepository;
    }

    public List<PostDto> getRecentPosts(int postCount) {
        PageRequest pageRequest = PageRequest.of(0, postCount);
        List<Post> recentPosts = postRepository.findAllByOrderByCreatedDateDesc(pageRequest);

        return asStream(recentPosts)
                .map(PostDto::createFromEntity)
                .collect(toList());
    }

    /**
     * 내 포스트 정보 가져오기
     */
    public List<PostDto> getMemberPosts(String name) {
        List<Post> memberPosts = postRepository.findByMemberName(name);

        return asStream(memberPosts)
                .map(PostDto::createFromEntity)
                .collect(toList());
    }

    @Transactional
    public long save(PostSaveRequestDto postSaveRequestDto) {
        Member user = memberRepository.findByName(postSaveRequestDto.getMember()).orElse(new Member());
        Category category = categoryRepository.findByName(postSaveRequestDto.getCategory());

        Post post = Post.create(postSaveRequestDto.getTitle(), postSaveRequestDto.getContent(), user, category);

        return postRepository.save(post).getId();
    }
}
