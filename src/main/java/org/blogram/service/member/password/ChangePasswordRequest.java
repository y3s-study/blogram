package org.blogram.service.member.password;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class ChangePasswordRequest {
    @NotBlank(message = "현재 비밀번호를 입력하세요")
    private String currentPassword;
    @NotBlank(message = "변경할 비밀번호를 입력하세요")
    private String newPassword;
}
