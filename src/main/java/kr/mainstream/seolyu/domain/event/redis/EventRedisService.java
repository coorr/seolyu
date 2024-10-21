package kr.mainstream.seolyu.domain.event.redis;

import kr.mainstream.seolyu.domain.event.exception.DuplicatedEventException;
import kr.mainstream.seolyu.domain.event.exception.EventQuantityException;
import kr.mainstream.seolyu.infrastructure.redis.RedisRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static kr.mainstream.seolyu.infrastructure.redis.RedisEnv.EVENT_REQUEST_WITH_EVENT_ID_PREFIX;

@Service
@RequiredArgsConstructor
public class EventRedisService {
    private final RedisRepository redisRepository;

    public void add(Long eventId, String email, long ttl) {
        String key = generateKey(eventId);
        redisRepository.sAdd(key, email);
        redisRepository.setExpire(key, ttl);
    }

    public void checkEventQuantityAndDuplicate(EventRedisEntity event, String email) {
        if (!availableTotalQuantity(event.getMaxParticipants(), event.getId())) {
            throw new EventQuantityException();
        }
        if (!availableEmailDuplication(email, event.getId())) {
            throw new DuplicatedEventException();
        }
    }

    private boolean availableTotalQuantity(int maxQuantity, Long eventId) {
        String key = generateKey(eventId);
        return maxQuantity > redisRepository.sCard(key);
    }

    private boolean availableEmailDuplication(String email, Long eventId) {
        String key = generateKey(eventId);
        return !redisRepository.sIsEmail(key, email);
    }

    private String generateKey(Long eventId) {
        return EVENT_REQUEST_WITH_EVENT_ID_PREFIX.formatted(eventId);
    }
}
