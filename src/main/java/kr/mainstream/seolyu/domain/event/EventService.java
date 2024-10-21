package kr.mainstream.seolyu.domain.event;

import kr.mainstream.seolyu.domain.event.dto.EventGetResDto;
import kr.mainstream.seolyu.domain.event.exception.NotFoundEventException;
import kr.mainstream.seolyu.domain.event.redis.EventCacheService;
import kr.mainstream.seolyu.domain.event.redis.EventRedisEntity;
import kr.mainstream.seolyu.domain.event.redis.EventRedisService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class EventService {
    private final EventRepository eventRepository;
    private final EventQueryRepository eventQueryRepository;
    private final EventCacheService eventCacheService;
    private final EventRedisService eventRedisService;

    public EventGetResDto getValidEventsByCategory(EventCategory category) {
        return eventQueryRepository.findByCategoryAndIsAvailable(category, LocalDateTime.now())
                .orElseThrow(NotFoundEventException::new);
    }

    public void apply(Long eventId, String email, LocalDateTime currentDateTime) {
        EventRedisEntity event = eventCacheService.getAvailableEvent(eventId, currentDateTime);
        event.validateEventPeriod(currentDateTime);
        eventRedisService.checkEventQuantityAndDuplicate(event, email);
        long ttl = Duration.between(currentDateTime, event.getEndedAt()).getSeconds();
        eventRedisService.add(eventId, email, ttl);
    }

    public void applyMq(Long eventId, String email, LocalDateTime currentDateTime) {
        EventRedisEntity event = eventCacheService.getAvailableEvent(eventId, currentDateTime);
        event.validateEventPeriod(currentDateTime);
        eventRedisService.checkEventQuantityAndDuplicate(event, email);
        long ttl = Duration.between(currentDateTime, event.getEndedAt()).getSeconds();
        eventRedisService.add(eventId, email, ttl);
    }
}
