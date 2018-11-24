package org.blogram.service.member;

import lombok.Getter;
import org.blogram.domain.member.Member;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Getter
public class MemberRequestDto {

    private Long id;

    @NotBlank(message = "이름을 작성해주세요.")
    private String name;

    @NotBlank(message = "메일을 작성해주세요.")
    @Email(message = "메일의 양식을 지켜주세요.")
    private String email;

    @NotBlank(message = "비밀번호를 작성해주세요.")
    private String password;

    private LocalDate birthDate;

    public Member toEntity() {
        return Member.builder()
                .name(name)
                .email(email)
                .password(password)
                .birthDate(birthDate)
                .build();
    }

}
