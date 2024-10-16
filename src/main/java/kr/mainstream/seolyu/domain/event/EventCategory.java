package kr.mainstream.seolyu.domain.event;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum EventCategory {
    RESUME_REVIEW("이력서 검토");

    private final String name;
}
