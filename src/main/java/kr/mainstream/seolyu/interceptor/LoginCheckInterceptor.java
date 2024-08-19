package kr.mainstream.seolyu.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.mainstream.seolyu.domain.reviewer.ReviewerJoinStatus;
import kr.mainstream.seolyu.domain.reviewer.exception.NotAvailableWithdrawException;
import kr.mainstream.seolyu.login.ExpiredLoginSessionException;
import kr.mainstream.seolyu.login.LoginInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.HandlerInterceptor;


@RequiredArgsConstructor
@Slf4j
@Component
public class LoginCheckInterceptor implements HandlerInterceptor {

    public static final String LOGIN_ATTRIBUTE_NAME = "loginInfo";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String uri = request.getRequestURI();
        log.debug("\n------------------------ LoginCheckInterceptor ----------------------------------\n*** URI: {}", uri);

        if (request.getMethod().equals(RequestMethod.OPTIONS.name())) {
            log.info("Request method :: OPTIONS -> LoginCheckInterceptor PASS {} ", request.getRequestURL());
            return true; // OPTIONS 요청일 경우 로그인 인터셉터 패스.
        }

        LoginInfo loginInfo = (LoginInfo) request.getAttribute(LOGIN_ATTRIBUTE_NAME);
        if (ObjectUtils.isEmpty(loginInfo)) {
            throw new ExpiredLoginSessionException();
        }

        if (ReviewerJoinStatus.WITHDRAWAL.equals(loginInfo.getJoinStatus())) {
            throw new NotAvailableWithdrawException();
        }
        return true;
    }
}
