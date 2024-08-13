package kr.mainstream.seolyu.infrastructure.email;

import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EmailMessageSender {
    private final JavaMailSender javaMailSender;

    @Async("emailTaskExecutor")
    public void send(MimeMessage message) {
        javaMailSender.send(message);
    }
}
