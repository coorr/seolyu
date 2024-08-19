package kr.mainstream.seolyu.domain.resumeReview.status;

import jakarta.mail.internet.MimeMessage;
import kr.mainstream.seolyu.domain.resumeReview.ResumeReview;
import kr.mainstream.seolyu.domain.resumeReview.ResumeReviewService;
import kr.mainstream.seolyu.infrastructure.email.EmailMessageBuilder;
import kr.mainstream.seolyu.infrastructure.email.EmailMessageSender;
import kr.mainstream.seolyu.infrastructure.email.template.EmailTemplate;
import kr.mainstream.seolyu.infrastructure.email.template.ReportCancelEmailTemplate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ResumeReviewCancelHandler implements ResumeReviewStatusHandler {
    private final ResumeReviewService resumeReviewService;
    private final EmailMessageBuilder emailMessageBuilder;
    private final EmailMessageSender emailMessageSender;

    @Override
    public void handle(String id) {
        ResumeReview resumeReview = resumeReviewService.getResumeReview(id);

        EmailTemplate emailTemplate = ReportCancelEmailTemplate.create(resumeReview.getApplicant().getName());
        MimeMessage message = emailMessageBuilder.build(resumeReview.getApplicant().getEmail(), emailTemplate);
        emailMessageSender.send(message);
    }
}
