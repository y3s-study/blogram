package org.blogram.service.post;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.blogram.domain.post.Post;

import java.time.LocalDateTime;

@Getter
@Setter(AccessLevel.PRIVATE)
public class PostDto {
	private Long id;
	private String title;
	private String content;
	private LocalDateTime createdDate;
	private LocalDateTime modifiedDate;
	private String authorName;

	static PostDto createFromEntity(Post post) {
		PostDto postDto = new PostDto();
		postDto.setId(post.getId());
		postDto.setTitle(post.getTitle());
		postDto.setContent(post.getContent());
		postDto.setCreatedDate(post.getCreatedDate());
		postDto.setModifiedDate(post.getModifiedDate());
		postDto.setAuthorName(post.getMember().getName());
		return postDto;
	}
}
