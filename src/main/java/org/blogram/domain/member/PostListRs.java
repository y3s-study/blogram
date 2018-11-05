package org.blogram.domain.member;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
/**
 * 멤버 포스트 리스트 Rs
 **/
public class PostListRs {
    private Integer id;
    private String title;
    private String content;
    private Integer deleted;
    private Integer likeCount;
    private Integer hateCount;
    private Integer memberId;
    private Integer categoryId;
    private String createdDate;
    private String modifiedDate;
}
