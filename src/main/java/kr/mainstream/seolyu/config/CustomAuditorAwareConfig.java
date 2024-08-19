package kr.mainstream.seolyu.config;

import jakarta.servlet.http.HttpServletRequest;
import kr.mainstream.seolyu.interceptor.LoginCheckInterceptor;
import kr.mainstream.seolyu.login.LoginInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.Optional;

@Configuration
@EnableJpaAuditing
@RequiredArgsConstructor
public class CustomAuditorAwareConfig implements AuditorAware<Long> {

    private final HttpServletRequest request;

    @Override
    public Optional<Long> getCurrentAuditor() {
        LoginInfo loginInfo = (LoginInfo) request.getAttribute(LoginCheckInterceptor.LOGIN_ATTRIBUTE_NAME);
        if (loginInfo == null) {
            return Optional.ofNullable(1001L);
        }
        return Optional.ofNullable(loginInfo.getReviewerId());
    }
}
