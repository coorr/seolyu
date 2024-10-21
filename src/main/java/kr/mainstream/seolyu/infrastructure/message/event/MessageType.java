package kr.mainstream.seolyu.infrastructure.message.event;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum MessageType {
    EVENT_APPLICANT("seolyu-event.exchange", "seolyu-event.key");

    private final String exchange;
    private final String routingKey;
}
