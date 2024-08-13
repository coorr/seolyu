package kr.mainstream.seolyu.domain.member.my;

import kr.mainstream.seolyu.domain.member.MemberRole;
import kr.mainstream.seolyu.login.LoginInfo;
import lombok.Getter;

@Getter
public class MemberMySummaryGetResDto {
    private Long memberId;
    private String email;
    private String name;
    private MemberRole role;

    public MemberMySummaryGetResDto(LoginInfo loginInfo) {
        this.memberId = loginInfo.getMemberId();
        this.email = loginInfo.getEmail();
        this.name = loginInfo.getName();
        this.role = loginInfo.getRole();
    }
}
