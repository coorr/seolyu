package kr.mainstream.seolyu.infrastructure.email;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import kr.mainstream.seolyu.infrastructure.email.template.EmailTemplate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class EmailMessageBuilder {
    private final JavaMailSender javaMailSender;

    public MimeMessage build(String to, EmailTemplate emailTemplate){
        MimeMessage message = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper messageHelper = new MimeMessageHelper(message, false, "UTF-8");
            messageHelper.setTo(to);
            messageHelper.setSubject(emailTemplate.getSubject());
            messageHelper.setText(emailTemplate.getContent());
            return message;
        } catch (MessagingException e) {
            log.error("이메일 메시지 생성 실패: {}", e.getMessage());
            throw new EmailMessageCreateException();
        }
    }
}

