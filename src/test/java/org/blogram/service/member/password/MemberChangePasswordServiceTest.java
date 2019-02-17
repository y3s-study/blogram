package org.blogram.service.member.password;

import org.blogram.domain.member.Member;
import org.blogram.repository.member.MemberRepository;
import org.blogram.service.ValidCustomException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
public class MemberChangePasswordServiceTest {
    @InjectMocks
    private MemberChangePasswordService sut;
    @Mock
    private MemberRepository memberRepository;
    @Mock
    private PasswordEncoder passwordEncoder;

    private Member testMember;

    @Before
    public void setUp() {
        testMember = Member.builder()
                .name("testUser")
                .password("currentPassword")
                .build();
    }

    @Test(expected = NullPointerException.class)
    public void userNameConNotBeNull() {
        sut.changePassword(null, new ChangePasswordRequest());
        verify(memberRepository, never()).findByName(anyString());
    }

    @Test(expected = NullPointerException.class)
    public void changePasswordRequestDtoCanNotBeNull() {
        sut.changePassword("testUser", null);
        verify(memberRepository, never()).findByName(anyString());
    }

    @Test(expected = ValidCustomException.class)
    public void shouldThrowExceptionWithWrongPassword() {
        when(memberRepository.findByName(eq("testUser"))).thenReturn(Optional.of(testMember));
        when(passwordEncoder.matches(anyString(), anyString())).thenReturn(false);

        sut.changePassword("testUser", getChangePasswordRequest());
    }

    @Test
    public void passwordShouldChangeSuccessfully() {
        when(memberRepository.findByName(eq("testUser"))).thenReturn(Optional.of(testMember));
        when(passwordEncoder.matches(anyString(), anyString())).thenReturn(true);

        sut.changePassword("testUser", getChangePasswordRequest());
    }

    private static ChangePasswordRequest getChangePasswordRequest() {
        ChangePasswordRequest changePasswordRequest = new ChangePasswordRequest();
        changePasswordRequest.setCurrentPassword("currentPassword");
        changePasswordRequest.setNewPassword("newPassword");
        return changePasswordRequest;
    }
}