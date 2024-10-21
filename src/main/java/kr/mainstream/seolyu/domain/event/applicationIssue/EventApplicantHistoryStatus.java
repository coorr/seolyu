package kr.mainstream.seolyu.domain.event.applicationIssue;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum EventApplicantHistoryStatus {
    SUCCESS,
    FAIL
}
