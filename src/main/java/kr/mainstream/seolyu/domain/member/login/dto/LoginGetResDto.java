package kr.mainstream.seolyu.domain.member.login.dto;

import kr.mainstream.seolyu.domain.member.Member;
import kr.mainstream.seolyu.domain.member.MemberRole;
import lombok.Getter;

@Getter
public class LoginGetResDto {
    private Long memberId;
    private String email;
    private String name;
    private MemberRole role;

    public LoginGetResDto(Member member) {
        this.memberId = member.getId();
        this.email = member.getEmail();
        this.name = member.getName();
        this.role = member.getRole();
    }
}
