package kr.mainstream.seolyu.domain.resumeReview.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ResumeReviewPostReqDto {
    @NotBlank(message = "이름을 입력해주세요.")
    private String name;

    @NotBlank(message = "이메일을 입력해주세요.")
    private String email;

    @NotBlank(message = "포지션을 선택해주세요.")
    private String position;

    private String httpUrl;
    private String requestDetails;
}
