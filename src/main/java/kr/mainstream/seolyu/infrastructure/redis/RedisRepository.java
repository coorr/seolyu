package kr.mainstream.seolyu.infrastructure.redis;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.concurrent.TimeUnit;

@RequiredArgsConstructor
@Repository
public class RedisRepository {

    private final RedisTemplate<String, Object> redisTemplate;

    public Long sAdd(String key, String value) {
        return redisTemplate.opsForSet().add(key, value);
    }

    public void setExpire(String key, long ttl) {
        redisTemplate.expire(key, ttl, TimeUnit.SECONDS);
    }

    public Long sCard(String key) {
        return redisTemplate.opsForSet().size(key);
    }

    public Boolean sIsEmail(String key, String value) {
        return redisTemplate.opsForSet().isMember(key, value);
    }

    public Boolean delete(String key) {
        return redisTemplate.delete(key);
    }

    public void publish(String topic, Object message){
        redisTemplate.convertAndSend(topic, message);
    }

}
