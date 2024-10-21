package kr.mainstream.seolyu.infrastructure.message.event;

import kr.mainstream.seolyu.infrastructure.message.Message;
import kr.mainstream.seolyu.infrastructure.message.MessageSender;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EventMessageSender implements MessageSender {

    private final RabbitTemplate rabbitTemplate;

    @Override
    public void send(Message message) {
        MessageType type = message.getType();
        rabbitTemplate.convertAndSend(type.getExchange(), type.getRoutingKey(), message.getMessage());
    }

    @Async("rabbitTaskExecutor")
    public void asyncSend(Message message) {
        MessageType type = message.getType();
        rabbitTemplate.convertAndSend(type.getExchange(), type.getRoutingKey(), message.getMessage());
    }
}
