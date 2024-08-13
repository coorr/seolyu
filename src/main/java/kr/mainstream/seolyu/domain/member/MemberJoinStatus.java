package kr.mainstream.seolyu.domain.member;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MemberJoinStatus {
    JOINED("이용중"),
    DENIED("사용불가"),
    WITHDRAWAL("탈퇴");

    private final String status;
}
