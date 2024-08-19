package kr.mainstream.seolyu.domain.reviewer.login.dto;

import kr.mainstream.seolyu.domain.reviewer.Reviewer;
import kr.mainstream.seolyu.domain.reviewer.ReviewerRole;
import lombok.Getter;

@Getter
public class LoginGetResDto {
    private Long reviewerId;
    private String email;
    private String name;
    private ReviewerRole role;

    public LoginGetResDto(Reviewer reviewer) {
        this.reviewerId = reviewer.getId();
        this.email = reviewer.getEmail();
        this.name = reviewer.getName();
        this.role = reviewer.getRole();
    }
}
