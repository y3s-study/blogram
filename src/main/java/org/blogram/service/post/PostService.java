package org.blogram.service.post;

import org.blogram.domain.post.Post;
import org.blogram.repository.post.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;

import static java.util.stream.Collectors.toList;
import static org.blogram.util.BlogramStreamUtils.asStream;

@Service
public class PostService {
	private final PostRepository postRepository;

	@Autowired
	public PostService(PostRepository postRepository) {
		this.postRepository = postRepository;
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
    public List<PostDto> getMyPosts(Principal principal) {
        List<Post> recentPosts = postRepository.findAll();

        return asStream(recentPosts).map(PostDto::createFromEntity)
                .filter(postDto -> postDto.getAuthorName().equals(principal.getName()))
                .collect(toList());
    }
}
