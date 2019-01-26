package org.blogram.service.member;

import lombok.Getter;
import org.blogram.domain.member.Member;

@Getter
public class MemberResponseDto {
    private Long id;
    private String name;
    private String email;

    public MemberResponseDto(Member member) {
        id = member.getId();
        name = member.getName();
        email = member.getEmail();
    }

}