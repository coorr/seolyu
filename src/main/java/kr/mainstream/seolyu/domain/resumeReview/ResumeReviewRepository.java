package kr.mainstream.seolyu.domain.resumeReview;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResumeReviewRepository extends JpaRepository<ResumeReview, String> {
}
