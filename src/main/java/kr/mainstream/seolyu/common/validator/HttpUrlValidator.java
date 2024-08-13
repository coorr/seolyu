package kr.mainstream.seolyu.common.validator;

        import org.springframework.stereotype.Component;
        import org.springframework.validation.Errors;
        import org.springframework.validation.Validator;

        import java.util.regex.Pattern;

@Component
public class HttpUrlValidator implements Validator {

    public static final Pattern HTTP_URL_PATTERN = Pattern.compile("(https?:\\/\\/(?:www\\.|(?!www))[a-zA-Z0-9][a-zA-Z0-9-]+[a-zA-Z0-9]\\.[^\\s]{2,}|www\\.[a-zA-Z0-9][a-zA-Z0-9-]+[a-zA-Z0-9]\\.[^\\s]{2,}|https?:\\/\\/(?:www\\.|(?!www))[a-zA-Z0-9]+\\.[^\\s]{2,}|www\\.[a-zA-Z0-9]+\\.[^\\s]{2,})");

    @Override
    public boolean supports(Class<?> clazz) {
        return String.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object o, Errors errors) {
        String url = (String) o;

        if (!HTTP_URL_PATTERN.matcher(url).matches()) {
            errors.rejectValue("httpUrl",null, "http:// 또는 https:// 를 포함해 형식에 맞는 URL을 입력해 주세요.");
        }
    }
}
