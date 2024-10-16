package kr.mainstream.seolyu.domain.event;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import kr.mainstream.seolyu.domain.event.dto.EventGetResDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

import static kr.mainstream.seolyu.domain.event.QEvent.event;

@Repository
@RequiredArgsConstructor
public class EventQueryRepository {
    private final JPAQueryFactory factory;

    public Optional<EventGetResDto> findByCategoryAndIsAvailable(EventCategory category, LocalDateTime dateTime) {
        return Optional.ofNullable(
                factory.select(Projections.constructor(EventGetResDto.class,
                        event.id,
                        event.name
                ))
                .from(event)
                .where(
                        event.category.eq(category),
                        event.startedAt.loe(dateTime),
                        event.endedAt.goe(dateTime)
                )
                .fetchOne());
    }
}
