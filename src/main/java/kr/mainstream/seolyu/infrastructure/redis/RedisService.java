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

    public static final String MEMBER_ID = "member_id";
    public static final String CLIENT_ID = "client_id";

    private final StringRedisTemplate redisTemplate;

    public LoginSession getLoginSession(String referenceKey) {
        Map<String, String> map = redisTemplate.<String, String>opsForHash().entries(referenceKey);
        String memberId = map.get(MEMBER_ID);
        String clientId = map.get(CLIENT_ID);

        if (StringUtils.hasText(memberId) && StringUtils.hasText(clientId)) {
            return new LoginSession(Long.valueOf(memberId), clientId);
        }
        return null;
    }

    public void extendExpiredTime(String referenceKey) {
        redisTemplate.expire(referenceKey, RedisEnv.ACCESS_KEY_TTL, TimeUnit.DAYS);
    }

    public void createLoginSession(String referenceKey, LoginSession loginSession) {
        Map<String, String> map = new HashMap<>();
        map.put(MEMBER_ID, String.valueOf(loginSession.getMemberId()));
        map.put(CLIENT_ID, loginSession.getClientId());

        redisTemplate.opsForHash().putAll(referenceKey, map);
        extendExpiredTime(referenceKey);
    }

    public void deleteLogoutSession(String referenceKey) {
        redisTemplate.delete(referenceKey);
    }
}
