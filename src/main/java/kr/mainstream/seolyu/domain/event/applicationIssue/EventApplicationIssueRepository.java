package kr.mainstream.seolyu.domain.event.applicationIssue;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventApplicationIssueRepository extends JpaRepository<EventApplicationIssue, Long> {
}
