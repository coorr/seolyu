package kr.mainstream.seolyu.infrastructure.message.event.builder;

import kr.mainstream.seolyu.infrastructure.message.MessageBuilder;
import lombok.Getter;

@Getter
public abstract class EventMessageBuilderHelper<B> implements MessageBuilder {
    private Boolean drill = Boolean.FALSE;
    private Object parameter;

    public B drill(Boolean drill) {
        this.drill = drill;
        return (B) this;
    }

    public B parameter(Object parameter) {
        this.parameter = parameter;
        return (B) this;
    }
}
