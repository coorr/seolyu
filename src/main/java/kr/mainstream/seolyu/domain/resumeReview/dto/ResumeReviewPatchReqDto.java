package kr.mainstream.seolyu.domain.resumeReview.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResumeReviewPatchReqDto {
    private String status;
}
