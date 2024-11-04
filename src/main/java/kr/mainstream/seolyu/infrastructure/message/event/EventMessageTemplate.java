package kr.mainstream.seolyu.infrastructure.message.event;

import kr.mainstream.seolyu.infrastructure.message.common;
import lombok.Getter;

@Getter
public class EventMessageTemplate extends common {
    private final String templateType;
    public EventMessageTemplate(Boolean drill, Object parameter, String templateType) {
        super(drill, parameter);
        this.templateType = templateType;
    }
}
