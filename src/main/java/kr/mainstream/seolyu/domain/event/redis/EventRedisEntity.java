package kr.mainstream.seolyu.domain.event.redis;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import kr.mainstream.seolyu.domain.event.Event;
import kr.mainstream.seolyu.domain.event.EventCategory;
import kr.mainstream.seolyu.domain.event.exception.EventPeriodException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class EventRedisEntity {
    private Long id;
    private String name;
    private EventCategory category;
    private int maxParticipants;

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime startedAt;
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime endedAt;

    public EventRedisEntity(Event event) {
        this.id = event.getId();
        this.name = event.getName();
        this.category = event.getCategory();
        this.maxParticipants = event.getMaxParticipants();
        this.startedAt = event.getStartedAt();
        this.endedAt = event.getEndedAt();
    }

    public void validateEventPeriod(LocalDateTime requestDateTime) {
        if (startedAt == null || endedAt == null) {
            throw new EventPeriodException();
        }

        if (requestDateTime.isBefore(startedAt) || requestDateTime.isAfter(endedAt)) {
            throw new EventPeriodException();
        }
    }
}
