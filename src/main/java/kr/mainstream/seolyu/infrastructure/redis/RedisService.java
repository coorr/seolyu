package kr.mainstream.seolyu.infrastructure.redis;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
@RequiredArgsConstructor
public class RedisService {

    public static final String REVIEWER_ID = "reviewer_id";
    public static final String CLIENT_ID = "client_id";

    private final StringRedisTemplate redisTemplate;

    public LoginSession getLoginSession(String referenceKey) {
        Map<String, String> map = redisTemplate.<String, String>opsForHash().entries(referenceKey);
        String reviewerId = map.get(REVIEWER_ID);
        String clientId = map.get(CLIENT_ID);

        if (StringUtils.hasText(reviewerId) && StringUtils.hasText(clientId)) {
            return new LoginSession(Long.valueOf(reviewerId), clientId);
        }
        return null;
    }

    public void extendExpiredTime(String referenceKey) {
        redisTemplate.expire(referenceKey, RedisEnv.ACCESS_KEY_TTL, TimeUnit.DAYS);
    }

    public void createLoginSession(String referenceKey, LoginSession loginSession) {
        Map<String, String> map = new HashMap<>();
        map.put(REVIEWER_ID, String.valueOf(loginSession.getReviewerId()));
        map.put(CLIENT_ID, loginSession.getClientId());

        redisTemplate.opsForHash().putAll(referenceKey, map);
        extendExpiredTime(referenceKey);
    }

    public void deleteLogoutSession(String referenceKey) {
        redisTemplate.delete(referenceKey);
    }
}
