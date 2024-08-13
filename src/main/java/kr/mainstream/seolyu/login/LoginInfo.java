package kr.mainstream.seolyu.login;

import kr.mainstream.seolyu.domain.member.Member;
import kr.mainstream.seolyu.domain.member.MemberJoinStatus;
import kr.mainstream.seolyu.domain.member.MemberRole;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class LoginInfo {
    private Long memberId;
    private String clientId;
    private String email;
    private String name;
    private MemberRole role;
    private MemberJoinStatus joinStatus;

    public LoginInfo(Member member, String clientId) {
        this.memberId = member.getId();
        this.clientId = clientId;
        this.email = member.getEmail();
        this.name = member.getName();
        this.role = member.getRole();
        this.joinStatus = member.getJoinStatus();
    }
}
