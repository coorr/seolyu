package kr.mainstream.seolyu.domain.resumeReview.status;

import jakarta.mail.internet.MimeMessage;
import kr.mainstream.seolyu.domain.resumeReview.exception.RequiredFeedbackDataMissingException;
import kr.mainstream.seolyu.domain.resumeReview.ResumeReview;
import kr.mainstream.seolyu.domain.resumeReview.ResumeReviewService;
import kr.mainstream.seolyu.infrastructure.email.EmailMessageBuilder;
import kr.mainstream.seolyu.infrastructure.email.EmailMessageSender;
import kr.mainstream.seolyu.infrastructure.email.template.EmailTemplate;
import kr.mainstream.seolyu.infrastructure.email.template.ReportCompleteEmailTemplate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
@RequiredArgsConstructor
public class ResumeReviewCompletedHandler implements ResumeReviewStatusHandler {
    private final ResumeReviewService resumeReviewService;
    private final EmailMessageBuilder emailMessageBuilder;
    private final EmailMessageSender emailMessageSender;

    @Override
    public void handle(String id) {
        ResumeReview resumeReview = resumeReviewService.getResumeReview(id);

        if (!isHasText(resumeReview.getSummary()) || !isHasText(resumeReview.getComment()) ||
                !isHasText(resumeReview.getInitialTitle()) || !isHasText(resumeReview.getInitialContent())) {
            throw new RequiredFeedbackDataMissingException();
        }

        EmailTemplate emailTemplate = ReportCompleteEmailTemplate.create(resumeReview.getApplicant().getName(), id);
        MimeMessage message = emailMessageBuilder.build(resumeReview.getApplicant().getEmail(), emailTemplate);
        emailMessageSender.send(message);
    }

    private boolean isHasText(String text) {
        return StringUtils.hasText(text);
    }
}
