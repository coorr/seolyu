package kr.mainstream.seolyu.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.mainstream.seolyu.domain.reviewer.login.LoginService;
import kr.mainstream.seolyu.infrastructure.redis.LoginSession;
import kr.mainstream.seolyu.login.GatewayService;
import kr.mainstream.seolyu.login.LoginInfo;
import kr.mainstream.seolyu.login.RequestHeader;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import static kr.mainstream.seolyu.filter.RequestHeaderFilter.REQUEST_HEADER;


@Component
@Slf4j
@RequiredArgsConstructor
public class CookieInterceptor implements HandlerInterceptor {

    private final GatewayService gatewayService;
    private final LoginService loginService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.debug("\n------------------------ CookieCheckInterceptor ----------------------------------\n*** URI: {}", request.getRequestURI());
        RequestHeader requestHeader = (RequestHeader) request.getAttribute(REQUEST_HEADER);
        log.debug("*** 헤더 :: {}", requestHeader);

        String token;
        String clientId;

        if (requestHeader.isWebCall()) {
            token = gatewayService.getReviewerCookie(request.getCookies());
            clientId = gatewayService.getClientId(request);
        } else {
            if (!StringUtils.hasLength(requestHeader.getMid())) return true;
            token = String.valueOf(requestHeader.getReviewerId());
            clientId = requestHeader.getDeviceId();
        }

        String referenceKey = gatewayService.getReferenceKey(token);
        log.debug("*** referenceKey :: {}", referenceKey);
        if (!StringUtils.hasLength(referenceKey)) return true;

        LoginSession loginSession = gatewayService.getLoginSession(requestHeader, referenceKey, clientId);
        log.debug("*** loginSession :: {}", loginSession);

        if (!ObjectUtils.isEmpty(loginSession)) {
            LoginInfo loginInfo = null;
            try {
                loginInfo = loginService.getLoginInfo(loginSession.getReviewerId(), loginSession.getClientId());
            } catch (Exception e) {
                gatewayService.logout(loginSession.getReviewerId(), requestHeader.isWebCall(), request, response);
            }
            if (null != loginInfo) {
                gatewayService.extendLoginSession(referenceKey);
                request.setAttribute(LoginCheckInterceptor.LOGIN_ATTRIBUTE_NAME, loginInfo);
            }
        }
        return true;
    }
}
