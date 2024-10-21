package kr.mainstream.seolyu.domain.event;

import kr.mainstream.seolyu.domain.event.dto.EventGetResDto;
import kr.mainstream.seolyu.domain.event.exception.InvalidEventCategoryException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/events")
public class EventController {
    private final EventService eventService;

    @GetMapping("/category/{category}/valid")
    public ResponseEntity<EventGetResDto> getValidEventsByCategory(@PathVariable String category) {
        try {
            EventCategory.valueOf(category);
        } catch (Exception e) {
            throw new InvalidEventCategoryException();
        }
        return ResponseEntity.ok(eventService.getValidEventsByCategory(EventCategory.valueOf(category)));
    }

}
