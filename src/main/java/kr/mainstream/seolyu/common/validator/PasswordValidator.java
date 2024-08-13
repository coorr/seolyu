package kr.mainstream.seolyu.common.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.regex.Pattern;

@Component
public class PasswordValidator implements Validator {

    public static final String PASSWORD_PATTERN = "^(?=.*?[a-zA-Z])(?=.*?[0-9])(?=.*?[#?!@$ %^&*-]).{8,20}$";

    @Override
    public boolean supports(Class<?> clazz) {
        return String.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object o, Errors errors) {
        String password = (String) o;

        if (!Pattern.compile(PASSWORD_PATTERN).matcher(password).matches()) {
            errors.rejectValue("password", null, "영문, 숫자, 특수문자 포함 8-20자로 입력해 주세요");
        }
    }
}
