package kr.mainstream.seolyu.infrastructure.email;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import kr.mainstream.seolyu.infrastructure.email.template.EmailTemplate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;

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
            messageHelper.setFrom("seolyu.team@gmail.com", "Seolyu");  // 여기서 발신자 이름 설정
            return message;
        } catch (MessagingException | UnsupportedEncodingException e) {
            log.error("이메일 메시지 생성 실패: {}", e.getMessage());
            throw new EmailMessageCreateException();
        }
    }
}

