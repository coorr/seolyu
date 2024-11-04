package kr.mainstream.seolyu.domain.event;

import kr.mainstream.seolyu.common.exception.ValidationIllegalArgumentException;
import kr.mainstream.seolyu.domain.event.dto.EventGetListReqDto;
import kr.mainstream.seolyu.domain.event.dto.EventGetListResDto;
import kr.mainstream.seolyu.domain.event.validator.EventGetListReqDtoValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/events")
public class EventController {
    private final EventService eventService;
    private final EventGetListReqDtoValidator eventGetListReqDtoValidator;

    @GetMapping
    public ResponseEntity<List<EventGetListResDto>> getList(EventGetListReqDto dto, BindingResult bindingResult) {
        eventGetListReqDtoValidator.validate(dto, bindingResult);
        if (bindingResult.hasErrors()) {
            throw new ValidationIllegalArgumentException(bindingResult);
        }
        return ResponseEntity.ok(eventService.getList(dto));
    }
}
