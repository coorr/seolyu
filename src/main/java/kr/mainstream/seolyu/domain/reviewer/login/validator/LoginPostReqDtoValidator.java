package kr.mainstream.seolyu.domain.reviewer.login.validator;

import kr.mainstream.seolyu.common.validator.EmailValidator;
import kr.mainstream.seolyu.common.validator.PasswordValidator;
import kr.mainstream.seolyu.domain.reviewer.login.dto.LoginPostReqDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
@RequiredArgsConstructor
public class LoginPostReqDtoValidator implements Validator {

    private final EmailValidator emailValidator;
    private final PasswordValidator passwordValidator;

    @Override
    public boolean supports(Class<?> clazz) {
        return LoginPostReqDto.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object o, Errors errors) {
        LoginPostReqDto dto = (LoginPostReqDto) o;

        ValidationUtils.invokeValidator(emailValidator, dto.getEmail(), errors);
        ValidationUtils.invokeValidator(passwordValidator, dto.getPassword(), errors);
    }
}
