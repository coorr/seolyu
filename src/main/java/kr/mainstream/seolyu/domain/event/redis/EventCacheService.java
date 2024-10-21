package kr.mainstream.seolyu.domain.event.redis;

import kr.mainstream.seolyu.domain.event.Event;
import kr.mainstream.seolyu.domain.event.EventQueryRepository;
import kr.mainstream.seolyu.domain.event.exception.NotFoundEventException;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

import static kr.mainstream.seolyu.infrastructure.redis.RedisEnv.EVENT_KEY;

@Service
@RequiredArgsConstructor
public class EventCacheService {
    private final EventQueryRepository eventQueryRepository;

    @Cacheable(key = "#eventId", value = EVENT_KEY)
    public EventRedisEntity getAvailableEvent(Long eventId, LocalDateTime currentDateTime) {
        Event event = eventQueryRepository.findByIdAndIsAvailable(eventId, currentDateTime)
                .orElseThrow(NotFoundEventException::new);
        return new EventRedisEntity(event);
    }
}

