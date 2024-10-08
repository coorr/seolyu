package kr.mainstream.seolyu.login;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.mainstream.seolyu.common.security.EncryptService;
import kr.mainstream.seolyu.config.CookieProperties;
import kr.mainstream.seolyu.domain.reviewer.login.LoginService;
import kr.mainstream.seolyu.infrastructure.redis.LoginSession;
import kr.mainstream.seolyu.infrastructure.redis.RedisEnv;
import kr.mainstream.seolyu.infrastructure.redis.RedisService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.util.UUID;

import static kr.mainstream.seolyu.infrastructure.redis.RedisEnv.LOGIN_INFO_KEY;

@Service
@RequiredArgsConstructor
@Slf4j
public class GatewayService {
    private final CookieProperties cookieProperties;
    private final EncryptService encryptService;
    private final RedisService redisService;
    private final LoginService loginService;

    public static String generateKey(String prefix, String value) {
        return String.format("%s:%s", prefix, value);
    }

    public void saveCookie(String key, String value, HttpServletResponse response) {
        Cookie ck = new Cookie(key, value);
        ck.setPath("/");
        ck.setMaxAge(-1);
        ck.setHttpOnly(true);
        ck.setSecure(false);
        ck.setDomain(cookieProperties.getDomain());
        response.addCookie(ck);
    }

    public void deleteCookie(String key, HttpServletResponse response) {
        Cookie ck = new Cookie(key, "");
        ck.setPath("/");
        ck.setMaxAge(0);
        ck.setHttpOnly(true);
        ck.setSecure(false);
        ck.setDomain(cookieProperties.getDomain());
        response.addCookie(ck);
    }

    public String getReviewerCookie(Cookie[] cookies) {
        if (null == cookies) return null;

        for (Cookie cookie : cookies) {
            if (cookieProperties.getName().equals(cookie.getName()))
                return cookie.getValue();
        }
        return null;
    }

    public String getClientId(HttpServletRequest request) {
        String clientIP = request.getHeader("X-Real-IP");
        if (!StringUtils.hasLength(clientIP)) {
            clientIP = request.getRemoteAddr();
        }
        return clientIP;
    }

    public String getReferenceKey(String token) {
        if (StringUtils.hasText(token)) {
            try {
                token = encryptService.digest(token);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

            return GatewayService.generateKey(RedisEnv.REFERENCE_KEY_PREFIX, token);
        }
        return null;
    }

    public LoginSession getLoginSession(RequestHeader requestHeader, String referenceKey, String clientId) {
        LoginSession loginSession = redisService.getLoginSession(referenceKey);
        if (ObjectUtils.isEmpty(loginSession)) {
            return null;
        }

        if (!clientId.equals(loginSession.getClientId())) {
            return null;
        }

        if (requestHeader.isAppCall()) {
            if (ObjectUtils.isEmpty(loginSession.getReviewerId())) {
                return null;
            }
            if (!requestHeader.getReviewerId().equals(loginSession.getReviewerId())) {
                return null;
            }
        }

        return loginSession;
    }

    public void extendLoginSession(String referenceKey) {
        redisService.extendExpiredTime(referenceKey);
    }

    public LoginInfo login(LoginInfo loginInfo, boolean isWebCall, HttpServletResponse response) {
        String token;
        if (isWebCall) {
            token = UUID.randomUUID().toString();
            saveCookie(cookieProperties.getName(), token, response);
        } else {
            token = String.valueOf(loginInfo.getReviewerId());
        }
        redisService.createLoginSession(this.getReferenceKey(token), new LoginSession(loginInfo.getReviewerId(), loginInfo.getClientId()));
        log.debug("[리뷰어] 로그인 loginInfo={}, isWebCall={}", loginInfo, isWebCall);

        return loginService.getLoginInfo(loginInfo.getReviewerId(), loginInfo.getClientId());
    }

    @CacheEvict(value = LOGIN_INFO_KEY, key = "#reviewerId")
    public void logout(Long reviewerId, boolean isWebCall, HttpServletRequest request, HttpServletResponse response) {
        String token;
        if (isWebCall) {
            token = this.getReviewerCookie(request.getCookies());
            deleteCookie(cookieProperties.getName(), response);
        } else {
            token = String.valueOf(reviewerId);
        }
        redisService.deleteLogoutSession(this.getReferenceKey(token));
        log.debug("[리뷰어] 로그아웃 reviewerId={}, isWebCall={}", reviewerId, isWebCall);
    }
}
