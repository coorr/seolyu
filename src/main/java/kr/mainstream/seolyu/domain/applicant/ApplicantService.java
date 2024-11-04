package kr.mainstream.seolyu.domain.applicant;

import kr.mainstream.seolyu.domain.applicant.dto.ApplicantCreateReqDto;
import kr.mainstream.seolyu.domain.event.EventService;
import kr.mainstream.seolyu.infrastructure.message.Message;
import kr.mainstream.seolyu.infrastructure.message.MessageSender;
import kr.mainstream.seolyu.infrastructure.message.event.TemplateType;
import kr.mainstream.seolyu.infrastructure.message.event.builder.EventMessageBuilderFactory;
import kr.mainstream.seolyu.infrastructure.message.event.parameter.EventApplicantCreateTemplateParameter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ApplicantService {
    private final EventService eventService;
    private final MessageSender eventMessageSender;

    public void save(ApplicantCreateReqDto dto) {
        eventService.apply(dto.getEventId(), dto.getEmail(), dto.getCurrentDateTime());

        Message message = new EventMessageBuilderFactory()
                .createBuilder(TemplateType.EVENT_APPLICANT_CREATED)
                .parameter(new EventApplicantCreateTemplateParameter(dto))
                .build();
        eventMessageSender.send(message);
    }
}
