package kr.mainstream.seolyu.domain.event.redis;

import kr.mainstream.seolyu.domain.event.Event;
import kr.mainstream.seolyu.domain.event.EventRepository;
import kr.mainstream.seolyu.domain.event.exception.NotFoundEventException;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import static kr.mainstream.seolyu.infrastructure.redis.RedisEnv.EVENT_KEY;

@Service
@RequiredArgsConstructor
public class EventCacheService {
    private final EventRepository eventRepository;

    @Cacheable(key = "#eventId", value = EVENT_KEY)
    public EventRedisEntity getEvent(long eventId) {
        Event event = eventRepository.findById(eventId)
                .orElseThrow(NotFoundEventException::new);
        return new EventRedisEntity(event);
    }
}

