package kr.mainstream.seolyu.domain.resumeReview;

import kr.mainstream.seolyu.domain.resumeReview.dto.ResumeReviewGetResDto;
import kr.mainstream.seolyu.domain.resumeReview.exception.ResumeReviewNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/resume-reviews")
public class ResumeReviewController {
    private final ResumeReviewService resumeReviewService;

    @GetMapping("/{id}")
    public ResponseEntity<ResumeReviewGetResDto> get(@PathVariable String id) {
        if (null == id) {
            throw new ResumeReviewNotFoundException();
        }
        return ResponseEntity.ok(resumeReviewService.getCompletedResumeReview(id));
    }
}
