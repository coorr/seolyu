package kr.mainstream.seolyu.domain.resumeFeedback;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResumeFeedbackRepository extends JpaRepository<ResumeFeedback, Long> {
}
