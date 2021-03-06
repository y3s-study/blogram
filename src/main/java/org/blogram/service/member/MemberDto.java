package org.blogram.service.member;

import lombok.Getter;
import lombok.Setter;
import org.blogram.domain.member.Member;

import java.time.LocalDate;

@Getter
@Setter
public class MemberDto {
    private Long id;
    private String name;
    private String email;
    private String password;
    private LocalDate birthDate;

    public MemberDto() {
    }

    public static MemberDto createFromEntity(Member member) {
        MemberDto memberDto = new MemberDto();
        memberDto.setId(member.getId());
        memberDto.setName(member.getName());
        memberDto.setEmail(member.getEmail());
        return memberDto;
    }

}
