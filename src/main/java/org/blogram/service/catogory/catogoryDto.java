package org.blogram.service.catogory;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.blogram.domain.category.Category;
import org.blogram.domain.member.Member;

@Getter
@Setter(AccessLevel.PRIVATE)
public class catogoryDto {
    private long id;
    private String name;
    private Member member;
    private Category category;

    @Builder
    public catogoryDto(String name, Member member, Category category) {
        this.name = name;
        this.member = member;
        this.category = category;
    }
}
