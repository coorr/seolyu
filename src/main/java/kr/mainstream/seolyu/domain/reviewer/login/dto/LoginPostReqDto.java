package kr.mainstream.seolyu.domain.reviewer.login.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class LoginPostReqDto {
    @NotBlank(message = "이메일을 입력해 주세요.")
    private String email;

    @NotBlank(message = "비밀번호을 입력해 주세요.")
    private String password;
}
