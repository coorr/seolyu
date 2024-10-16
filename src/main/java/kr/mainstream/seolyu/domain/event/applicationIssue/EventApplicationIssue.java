package kr.mainstream.seolyu.domain.event.applicationIssue;

import jakarta.persistence.*;
import kr.mainstream.seolyu.common.model.BaseEntityDateAggregate;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "event_application_issue")
@Getter
@Setter(AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class EventApplicationIssue extends BaseEntityDateAggregate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "e_id", nullable = false)
    private Long eventId;

    @Column(name = "a_id", nullable = false)
    private Long applicantId;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, length = 30)
    private EventApplicationIssueStatus status;

    public EventApplicationIssue(Long eventId, Long applicantId) {
        this.eventId = eventId;
        this.applicantId = applicantId;
        this.status = EventApplicationIssueStatus.SUCCESS;
    }
}
