package kr.mainstream.seolyu.domain.event.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class EventGetResDto {
    private Long id;
    private String name;
}
