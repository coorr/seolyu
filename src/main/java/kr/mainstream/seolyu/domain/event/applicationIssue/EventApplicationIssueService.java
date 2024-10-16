package kr.mainstream.seolyu.domain.event.applicationIssue;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class EventApplicationIssueService {
    private final EventApplicationIssueRepository eventApplicationIssueRepository;

    @Transactional
    public void save(EventApplicationIssue eventApplicationIssue) {
        eventApplicationIssueRepository.save(eventApplicationIssue);
    }
}
