package org.blogram.webservice.dto.posts;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.blogram.webservice.domain.posts.Posts;

@Getter
@Setter
@NoArgsConstructor
public class PostsSaveRequestDto {

    private String title;
    private String content;
    private String author;
//	private int test;

    public Posts toEntity(){
        return Posts.builder()
                .title(title)
                .content(content)
                .author(author)
                .build();
    }

}
