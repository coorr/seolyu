package kr.mainstream.seolyu.domain.event;

import kr.mainstream.seolyu.aop.DistributedLock;
import kr.mainstream.seolyu.domain.event.dto.EventGetListReqDto;
import kr.mainstream.seolyu.domain.event.dto.EventGetListResDto;
import kr.mainstream.seolyu.domain.event.redis.EventCacheService;
import kr.mainstream.seolyu.domain.event.redis.EventRedisEntity;
import kr.mainstream.seolyu.domain.event.redis.EventRedisService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EventService {
    private final EventRepository eventRepository;
    private final EventQueryRepository eventQueryRepository;
    private final EventCacheService eventCacheService;
    private final EventRedisService eventRedisService;

    public List<EventGetListResDto> getList(EventGetListReqDto dto) {
        return eventQueryRepository.findAllBySearchConditions(dto, LocalDateTime.now());
    }

    @DistributedLock(key = "#eventId")
    public void apply(Long eventId, String email, LocalDateTime currentDateTime) {
        EventRedisEntity event = eventCacheService.getAvailableEvent(eventId, currentDateTime);
        event.validateEventPeriod(currentDateTime);
        eventRedisService.checkEventQuantityAndDuplicate(event, email);
        long ttl = Duration.between(currentDateTime, event.getEndedAt()).getSeconds();
        eventRedisService.add(eventId, email, ttl);
    }
}
