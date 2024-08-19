package kr.mainstream.seolyu.login;

import kr.mainstream.seolyu.domain.reviewer.Reviewer;
import kr.mainstream.seolyu.domain.reviewer.ReviewerJoinStatus;
import kr.mainstream.seolyu.domain.reviewer.ReviewerRole;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class LoginInfo {
    private Long reviewerId;
    private String clientId;
    private String email;
    private String name;
    private ReviewerRole role;
    private ReviewerJoinStatus joinStatus;

    public LoginInfo(Reviewer reviewer, String clientId) {
        this.reviewerId = reviewer.getId();
        this.clientId = clientId;
        this.email = reviewer.getEmail();
        this.name = reviewer.getName();
        this.role = reviewer.getRole();
        this.joinStatus = reviewer.getJoinStatus();
    }
}
