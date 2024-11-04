package kr.mainstream.seolyu.domain.event;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import kr.mainstream.seolyu.domain.event.dto.EventGetListReqDto;
import kr.mainstream.seolyu.domain.event.dto.EventGetListResDto;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static kr.mainstream.seolyu.domain.event.QEvent.event;

@Repository
@RequiredArgsConstructor
public class EventQueryRepository {
    private final JPAQueryFactory factory;

    public List<EventGetListResDto> findAllBySearchConditions(EventGetListReqDto dto, LocalDateTime currentDateTime) {
        return factory
                .select(Projections.constructor(EventGetListResDto.class,
                        event.id,
                        event.name
                ))
                .from(event)
                .where(getSearchWhereClauses(dto, currentDateTime))
                .fetch();
    }

    private BooleanExpression[] getSearchWhereClauses(EventGetListReqDto dto, LocalDateTime currentDateTime) {
        return new BooleanExpression[]{
                eqCategory(dto.getCategory()),
                isDateWithinEventPeriod(dto.isActive(), currentDateTime)
        };
    }

    private BooleanExpression eqCategory(String category) {
        if (StringUtils.isBlank(category)) {
            return null;
        }
        return event.category.eq(EventCategory.valueOf(category));
    }

    private BooleanExpression isDateWithinEventPeriod(boolean isActive, LocalDateTime currentDateTime) {
        if (!isActive) {
            return null;
        }
        return event.startedAt.loe(currentDateTime).and(event.endedAt.goe(currentDateTime));
    }

    public Optional<Event> findByIdAndIsAvailable(Long id, LocalDateTime dateTime) {
        return Optional.ofNullable(factory.selectFrom(event)
                .from(event)
                .where(
                        event.id.eq(id),
                        event.startedAt.loe(dateTime),
                        event.endedAt.goe(dateTime)
                )
                .fetchOne());
    }
}
