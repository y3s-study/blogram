package org.blogram.service.post;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class PostSaveRequestDto {
    private Long id;
    private String title;
    private String content;
    private String category;
    private String member;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    @Builder
    public PostSaveRequestDto(String title, String content, String category, String member) {
        this.title = title;
        this.content = content;
        this.category = category;
        this.member = member;
    }
}
