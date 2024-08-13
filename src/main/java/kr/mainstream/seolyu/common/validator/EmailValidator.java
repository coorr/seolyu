package kr.mainstream.seolyu.common.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.regex.Pattern;

@Component
public class EmailValidator implements Validator {

    private static final String EMAIL_PATTERN = "(?i)(^[A-Z0-9]+([A-Z0-9_+-]+)*(\\.[A-Z0-9_+-]+)*)@([A-Z0-9_+-]+(\\.[A-Z0-9_+-]+)*)+\\.[A-Z]{2,6}$";

    @Override
    public boolean supports(Class<?> clazz) {
        return String.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object o, Errors errors) {
        String email = (String) o;

        if (!Pattern.compile(EMAIL_PATTERN).matcher(email).matches()) {
            errors.rejectValue("email",null, "잘못된 이메일 주소입니다. 다시 입력해 주세요.");
        }
    }
}
