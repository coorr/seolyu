package kr.mainstream.seolyu.infrastructure.message;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public abstract class common {
    private final String token = "event-message-listener-d604";
    private final Boolean drill;
    private final Object parameter;
}
