package kr.mainstream.seolyu.domain.event.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EventGetListReqDto {
    private String category;
    private boolean isActive = false;
}
